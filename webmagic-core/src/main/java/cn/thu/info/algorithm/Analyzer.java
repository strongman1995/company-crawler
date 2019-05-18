package cn.thu.info.algorithm;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class Analyzer {
    public static List<String> segment(String text){
        List<String> result = new ArrayList<String>();
        try {
            StringReader sr = new StringReader(text);
            IKSegmenter ik = new IKSegmenter(sr, true);
            Lexeme lex = null;
            while ((lex = ik.next()) != null) {
                result.add(lex.getLexemeText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
