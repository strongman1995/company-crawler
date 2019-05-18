package cn.thu.info.algorithm;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.*;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.util.List;


//bayesClassifer for juding the position of website units
public class ClassifierHelper {
    public static  Instance tempInstance;
    public static  Classifier bayesClassifier;
    static {
        try {
            File inputFile = new File("train.arff");
            ArffLoader atf = new ArffLoader();
            atf.setFile(inputFile);
            Instances instancesTrain = atf.getDataSet();

            inputFile = new File("train.arff");
            atf.setFile(inputFile);
            Instances instancesTest = atf.getDataSet();
            instancesTest.setClassIndex(6);
            instancesTrain.setClassIndex(6);
            bayesClassifier = (Classifier)SerializationHelper.read("NaiveBayes.model");
            tempInstance = instancesTest.instance(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int classify(List<Double> featureList) {
        for(int i=0; i<featureList.size(); i++){
            tempInstance.setValue(i, featureList.get(i));
        }
        double ans = 0.0;
        try {
            ans = bayesClassifier.classifyInstance(tempInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  (ans -0.0 <0.05)?0:1;
    }
}
