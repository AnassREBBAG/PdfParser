package org.example;

public class Question {
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String option5;
    public String option6;
    public String explanation1;
    public String explanation2;
    public String explanation3;
    public String explanation4;
    public String explanation5;
    public String explanation6;
    public String overallExplanation;
    public String correctAnswer;
    public String questionText;
    public String questionType;
    public String domain;


    public Question() {

        this.questionType = " ";


        this.questionText = " ";

        this.option1 = " ";
        this.explanation1 = " ";

        this.option2 = " ";
        this.explanation2 = " ";


        this.option3 = " ";
        this.explanation3 = " ";
        
        this.option4 = " ";
        this.explanation4 = " ";

        this.option5 = " ";
        this.explanation5 = " ";
        
        this.option6 = " ";
        this.explanation6 = " ";

        this.correctAnswer = " ";
        this.overallExplanation = " ";

        this.domain = " ";

    }





    @Override
    public String toString(){
        System.out.println("_______________________________");
        return "new question : " + "_a_" + this.questionText + "_b_"  + this.option1 + "_c_"  + this.option2 + "_d_"  + this.option3 + "_e_"  + this.option4 + "_f_"  + this.option5 + "_g_"  + this.option6 + "_h_"  + this.correctAnswer + "_i_"  + this.overallExplanation ;

    }


    public String[] toCSVDataLine(){
        return new String[] {this.questionText, "no type", 
        this.option1, this.explanation1, 
        this.option2, this.explanation2, 
        this.option3, this.explanation3,
        this.option4, this.explanation4, 
        this.option5, this.explanation5, 
        this.option6, this.explanation6,
        this.correctAnswer, this.overallExplanation,
        this.domain
    
    };
    }

    

  

}
