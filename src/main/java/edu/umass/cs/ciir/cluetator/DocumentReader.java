package edu.umass.cs.ciir.cluetator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: dietz
 * Date: 2/27/15
 * Time: 4:56 PM
 */
public class DocumentReader {
    public static List<ClueToken> read(BufferedReader filereader) throws IOException {

        ArrayList<ClueToken> tokens = new ArrayList<ClueToken>();

        for(String line = filereader.readLine(); line != null && line.length()>0; line = filereader.readLine()){
            tokens.add(lineToClueToken(line));
        }

        return tokens;
    }

    private static boolean noEntityAnnotation(String[] chunks){
        for(int i=2; i<chunks.length; i++) {
            if(chunks[i].trim().length()!=0) return false;
        }
        return true;
    }

    public static ClueToken lineToClueToken(String line){
        String[] chunks = line.split("\\t\\t");
        if(chunks.length<2) throw new RuntimeException("wrong format got \""+line+"\"");

        if(noEntityAnnotation(chunks)) {
            return new ClueToken(JSONUtil.unescape(chunks[0]), JSONUtil.unescape(chunks[1]));
        } else {

            List<String>[] chunkChunks = new List[5];
            for(int i=2; i<chunks.length; i++) {
                String[] innerChunks = chunks[i].split("\\t");
                ArrayList<String> innerChunkList = new ArrayList<String>(innerChunks.length);
                for(String str:innerChunks) {
                    innerChunkList.add(JSONUtil.unescape(str));
                }
                chunkChunks[i-2] = innerChunkList;
            }


            return new ClueToken(JSONUtil.unescape(chunks[0]), JSONUtil.unescape(chunks[1]),
                    chunkChunks[0], chunkChunks[1], chunkChunks[2], chunkChunks[3], chunkChunks[4]
                    );
        }
    }
}
