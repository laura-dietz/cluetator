package edu.umass.cs.ciir.cluetator;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * User: dietz
 * Date: 2/27/15
 * Time: 5:48 PM
 */
public class ClueDocumentApp {
    public static void main(String[] args) throws IOException {
        System.setProperty("file.encoding", "UTF-8"); // ESSENTIAL!!!!

        String dir = "/home/dietz/kbbridge/code/jjd-ede/queripidia/data/documentdump/query-201/";
        traverseDirectory(dir);

        String zipFile = "/home/dietz/kbbridge/code/jjd-ede/queripidia/data/documentdump/query-201.zip";
        traverseZipFile(zipFile);
    }

    private static void traverseDirectory(String dir) throws IOException {
        File dirFile = new File(dir);

        if(!dirFile.isDirectory() || dirFile.listFiles() == null) {
            throw new RuntimeException("dir " + dir + " is not a directory");
        }

        for(File file: dirFile.listFiles()) {
            System.out.println("-- "+ file.getName()+" --");

            printDocumentFromFile(file.getAbsolutePath());

            System.out.println("\n\n\n\n");
        }
    }

    private static void traverseZipFile(String zipFileName) throws IOException {
        ZipFile zipFile = new ZipFile(zipFileName);

        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while(enumeration.hasMoreElements()){
            ZipEntry entry = enumeration.nextElement();
            if(!entry.isDirectory()) {
                InputStream inputStream = zipFile.getInputStream(entry);
                System.out.println("-- " + entry.getName() + " --");

                printDocumentFromStream(inputStream);

                System.out.println("\n\n\n\n");
            }
        }
    }


    private static void printDocumentFromFile(String file) throws IOException {
        BufferedReader filereader = new BufferedReader(new FileReader(new File(file)));


        debugPrintClueTokensOfDocument(filereader);
    }

    private static void printDocumentFromStream(InputStream stream) throws IOException {
        BufferedReader filereader = new BufferedReader(new BufferedReader(new InputStreamReader(stream)));

        debugPrintClueTokensOfDocument(filereader);
    }





    private static void debugPrintClueTokensOfDocument(BufferedReader filereader) throws IOException {
        List<ClueToken> tokens = DocumentReader.read(filereader);

        int count = 20;
        for(ClueToken t : tokens){

            if(t.hasEntityAnnotation()){
                System.out.print("  [");

                for(String wikititle : t.getEntityWikiTitles()) {
                    System.out.print(wikititle + " ");
                }
                System.out.print("] ");
            }


            System.out.print(t.getNormToken());
            System.out.print(" ");

            count --;
            if (count <0) {
                System.out.println();
                count = 20;
            }
        }

        for(ClueToken t : tokens){

            if(t.hasEntityAnnotation()){
                System.out.print("\n[\n");
                System.out.println("t.getCapToken() = " + t.getCapToken());
                System.out.println("t.getNormToken() = " + t.getNormToken());
                System.out.println("t.getEntityMentions() = " + t.getEntityMentions());
                System.out.println("t.getEntityWikiTitles() = " + t.getEntityWikiTitles());
                System.out.println("t.getEntityFreebaseMids() = " + t.getEntityFreebaseMids());
                System.out.println("t.getWikiCategories() = " + t.getWikiCategories());
                System.out.println("t.getFreebaseTypes() = " + t.getFreebaseTypes());
                System.out.print("]\n");
            }

        }
    }
}
