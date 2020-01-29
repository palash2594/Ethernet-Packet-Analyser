import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PacketAnalyzer {
    /**
     * Driver function, which reads the packet data and coordinates with other classes.
     * @param args the name of the file.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Enter the packet name.)");
            System.exit(1);
        }

        FileInputStream fileInputStream = null;
        try {
            File file = new File(args[0]);
            fileInputStream = new FileInputStream(file);
            byte[] packetData = new byte[(int)file.length()];
            fileInputStream.read(packetData);

            System.out.println("ETHER:\t------- Ethernet Header -------");
            System.out.println("ETHER:\t");

            System.out.println("ETHER:\tPacket size = " + packetData.length + " bytes");

            EthernetHeader ethernetHeader = new EthernetHeader();
            ethernetHeader.getDestination(packetData[0], packetData[1], packetData[2], packetData[3], packetData[4], (byte) 255);
            ethernetHeader.getSource(packetData[6], packetData[7], packetData[8], packetData[9], packetData[10], packetData[11]);
            boolean flag = ethernetHeader.getEtherType(packetData[12], packetData[13]);
            if (!flag) {
                System.exit(1);
            }

            System.out.println();
            System.out.println("IP:\t\t------- IP Header -------");
            System.out.println("IP:\t\t");

            IPHeader ipHeader = new IPHeader();
            ipHeader.getVersion(packetData[14]);
            int headerLength = ipHeader.getIHL(packetData[14]);
            ipHeader.getTypeOfService(packetData[15]);
            ipHeader.getTotalLength(packetData[16], packetData[17]);
            ipHeader.getIdentification(packetData[18], packetData[19]);
            ipHeader.getFlag(packetData[20]);
            ipHeader.getFragmentOffset(packetData[20], packetData[21]);
            ipHeader.getTTL(packetData[22]);

            String protocol = ipHeader.getProtocol(packetData[23]);

            ipHeader.getHeaderCheckSum(packetData[24], packetData[25]);
            ipHeader.getSourceAddress(packetData[26], packetData[27], packetData[28], packetData[29]);
            ipHeader.getDestinationAddress(packetData[30], packetData[31], packetData[32], packetData[33]);

            if (headerLength > 20) {
                System.out.println("IP:\t\tOptions present.");
            } else {
                System.out.println("IP:\t\tNo options.");
            }

            System.out.println("IP:\t\t");

            // if options present then start reading after the ip header is finished.

            int startIndex = 14 /* start index of ip header */ + headerLength;

            if (protocol.equals("ICMP")) {

                System.out.println("\nICMP:\t------- " + protocol + " Header -------");

                ICMPHeader icmpHeader = new ICMPHeader();
                icmpHeader.getType(packetData[startIndex]);
                icmpHeader.getCode(packetData[startIndex + 1]);
                icmpHeader.getChecksum(packetData[startIndex + 2], packetData[startIndex + 3]);
                icmpHeader.getChecksum(packetData[startIndex + 2], packetData[startIndex + 3]);

            } else if (protocol.equals("TCP")) {

                System.out.println("\nTCP\t\t------- " + protocol + " Header -------");
                TCPHeader tcpHeader = new TCPHeader();

                tcpHeader.getSourcePort(packetData[startIndex], packetData[startIndex + 1]);
                tcpHeader.getDestinationPort(packetData[startIndex + 2], packetData[startIndex + 3]);
                tcpHeader.getSequenceNumber(packetData[startIndex + 4], packetData[startIndex + 5], packetData[startIndex + 6], packetData[startIndex + 7]);
                tcpHeader.getAckowledgementNumber(packetData[startIndex + 8], packetData[startIndex + 9], packetData[startIndex + 10], packetData[startIndex + 11]);

                int dataOffset = tcpHeader.getDataOffset(packetData[startIndex + 12]);

                tcpHeader.getFlags(packetData[startIndex + 13]);
                tcpHeader.getWindow(packetData[startIndex + 14], packetData[startIndex + 15]);
                tcpHeader.getChecksum(packetData[startIndex + 16], packetData[startIndex + 17]);
                tcpHeader.getUrgentPointer(packetData[startIndex + 18], packetData[startIndex + 19]);

                if (dataOffset > 20) {
                    System.out.println("TCP:\tOptions present.");
                } else {
                    System.out.println("TCP:\tNo options.");
                }

                System.out.println("TCP:\t");

                printData(packetData, startIndex + dataOffset * 4, protocol + ":\t");

            } else if (protocol.equals("UDP")) {

                System.out.println("\nUDP:\t------- " + protocol + " Header -------");

                UDPHeader udpHeader = new UDPHeader();
                udpHeader.getSourcePort(packetData[startIndex], packetData[startIndex + 1]);
                udpHeader.getDestinationPort(packetData[startIndex + 2], packetData[startIndex + 3]);
                udpHeader.getLength(packetData[startIndex + 4], packetData[startIndex + 5]);
                udpHeader.getChecksum(packetData[startIndex + 6], packetData[startIndex + 7]);

                printData(packetData, startIndex, protocol + ":\t");
            }

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }


    /**
     * to append one zero if the length of the hex data is one to make it two.
     * @param mac the hex value
     * @return hex value
     */
    public static String appendZeroesMac(String mac) {
        if (mac.length() < 2) {
            mac = "0" + mac;
        }
        return mac;
    }

    /**
     * to display the data in IP datagram.
     * @param packetData packet data in decimal.
     * @param startIndex the starting index.
     * @param toAppend the string to append.
     */
    public static void printData(byte[] packetData, int startIndex, String toAppend) {
        System.out.println(toAppend + "\n" + toAppend + "Data: (first 64 bytes)");
        int counter = 1;
        startIndex -= 1;

        String ascii = "";
        String hexData = toAppend;
        while (packetData.length > startIndex + counter && counter <= 64){
            if ((packetData[startIndex + counter] & 0xff) > 33 && (packetData[startIndex + counter] & 0xff) < 122) {
                hexData += String.format("%02x", packetData[startIndex + counter]);
                int num = (packetData[startIndex + counter] & 0xff);
                char c = (char) num;
                ascii += String.valueOf(c);
            } else {
                hexData += String.format("%02x", packetData[startIndex + counter]);
                ascii += ".";
            }
            if (counter % 2 == 0) {
                hexData += " ";
            }
            if (counter % 16 == 0) {
                System.out.println(hexData +"\t\t\t\t\t" + "'" + ascii + "'");
                ascii = "";
                hexData = toAppend;
            }
            counter += 1;
        }

        if (ascii.length() != 0) {
            if (hexData.length() < 40) {
                while (hexData.length() < 40) {
                    hexData += " ";
                }
            }
            System.out.println(hexData + "\t\t\t\t\t" + "'" + ascii + "'");
        }
    }
}
