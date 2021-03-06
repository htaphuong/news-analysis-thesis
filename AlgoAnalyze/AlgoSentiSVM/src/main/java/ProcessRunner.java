
import java.io.File;

import common.io.FileIO;
import config.PathConfigurationSentiSVM;
import utils.CommentExtractor;

/**
 * Created by Phuong Huynh on 6/27/2017.
 */
public class ProcessRunner {
    public void testTrain() throws Exception {
        CommentExtractor.TextToComment(1);
        CommentExtractor.CommentToCommentSVMandFeature();
        CommentExtractor.CommentSVMToTrainingSet();

        CommentExtractor.saveSVMFeatureSpace();

        SVMAnalyzer.train();
    }

    public void testTest() throws Exception {
        CommentExtractor.TextToComment(2);
        CommentExtractor.CommentToCommentSVMandFeature();
        CommentExtractor.CommentSVMToTestingSet();
        SVMAnalyzer.test();
    }

    public int predict(String content, String featureSpacePath, String inputSetPath, String trainingPath, String outputSetPath, String tokenizePropertyPath) throws Exception {
        CommentExtractor.loadSVMFeatureSpace(featureSpacePath);

        CommentExtractor.TextToComment(content);
        CommentExtractor.CommentToCommentSVMandFeature(tokenizePropertyPath);
        CommentExtractor.CommentSVMToPredictSet(inputSetPath);

        SVMAnalyzer.predict(inputSetPath,trainingPath,outputSetPath);
        
        /* Get the result from output file */
        //File result = new File("src/main/resources/" + PathConfigurationSentiSVM.output);
        //String resultPath =  result.getAbsolutePath();
        String resultPath = outputSetPath;
        
        FileIO.createReader(resultPath);
        String senti = FileIO.readLine();
        FileIO.closeReader();
        
        if (senti.trim().equals("-1.0"))
        	return -1;
        else if (senti.trim().equals("1.0"))
        	return 1;
        else if (senti.trim().equals("0.0"))
        	return 1;
        else return 0;
    }
    
    public int predictFinal(String content, String featureSpacePath, String inputSetPath, String trainingPath, String outputSetPath, String dictPath, String tokenizePropertyPath) throws Exception {
        CommentExtractor.loadSVMFeatureSpace(featureSpacePath);

        CommentExtractor.TextToComment(content);
        CommentExtractor.CommentToCommentSVMandFeature(tokenizePropertyPath);
        CommentExtractor.CommentSVMToPredictSetFinal(inputSetPath,dictPath,tokenizePropertyPath);

        SVMAnalyzer.predict(inputSetPath,trainingPath,outputSetPath);
        
        /* Get the result from output file */
        //File result = new File("src/main/resources/" + PathConfigurationSentiSVM.output);
        //String resultPath =  result.getAbsolutePath();
        String resultPath = outputSetPath;
        
        FileIO.createReader(resultPath);
        String senti = FileIO.readLine();
        FileIO.closeReader();
        
        if (senti.trim().equals("-1.0"))
        	return -1;
        else if (senti.trim().equals("1.0"))
        	return 1;
        else if (senti.trim().equals("0.0"))
        	return 1;
        else return 0;
    }
}
