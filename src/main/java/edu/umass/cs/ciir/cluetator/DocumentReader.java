package edu.umass.cs.ciir.cluetator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import static edu.umass.cs.ciir.cluetator.JSONStringEncoding.unescape;
import static edu.umass.cs.ciir.cluetator.NewlineEncoding.unescape;

/**
 * User: dietz
 * Date: 2/27/15
 * Time: 4:56 PM
 */
public class DocumentReader {
    public static List<ClueToken> read(BufferedReader filereader) throws IOException {

        ArrayList<ClueToken> tokens = new ArrayList<ClueToken>();

        boolean startOfSentence = false;
        for(String line = filereader.readLine(); line != null; line = filereader.readLine()){
            if(line.length()==0){
                startOfSentence = true;
            }  else {
                tokens.add(lineToClueToken(line, startOfSentence));
                startOfSentence = false;
            }
        }

        return tokens;
    }

    private static boolean noEntityAnnotation(String[] chunks){
        for(int i=2; i<chunks.length; i++) {
            if(chunks[i].trim().length()!=0) return false;
        }
        return true;
    }

    private static String[] mysplit(String line, String substring, int numEntries)  {
        int beginOffset = 0;
        int offset = 0;
        String[]  result = new String[numEntries];

        for(int i = 0; i < numEntries && offset != -1; i++){
            offset = line.indexOf(substring, beginOffset);
            if(offset == -1) offset = line.length();
            String chunk = line.substring(beginOffset, offset);
            result[i] = chunk;

            beginOffset = offset + substring.length();
        }

        return result;
    }

    public static ClueToken lineToClueToken(String line, boolean sentenceBoundary){
//        String[] chunks = line.split("\\t\\t");
        String[] chunks = mysplit(line, "\t\t",7);
        if(chunks.length<2) {
            throw new RuntimeException("wrong format got \""+line+"\"");
        }

        if(noEntityAnnotation(chunks)) {
            return new ClueToken(unescape(chunks[0]), unescape(chunks[1]), sentenceBoundary);
        } else {

            List<String>[] chunkChunks = new List[5];
            for(int i=2; i<chunks.length; i++) {
                String[] innerChunks = chunks[i].split("\\t");
                ArrayList<String> innerChunkList = new ArrayList<String>(innerChunks.length);
                for(String str:innerChunks) {
                    innerChunkList.add(unescape(str));
                }
                chunkChunks[i-2] = innerChunkList;
            }


            return new ClueToken(unescape(chunks[0]), unescape(chunks[1]),
                    chunkChunks[0], chunkChunks[1], chunkChunks[2], chunkChunks[3], chunkChunks[4],
                    sentenceBoundary
                    );
        }
    }
}
