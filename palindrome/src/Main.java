//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String str="mom";

        String newone=new String();

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                newone=newone+str.charAt(i);
            }
        }
        System.out.println(newone);

        int i=0;
        int j=newone.length()-1;
        boolean flag=true;
        while(i<=j){

            int first=newone.charAt(i);
            int last=newone.charAt(j);
            System.out.println(first+""+last);

            if(Math.abs(first-last)==0 ||(Math.abs(first-last)!=Math.min(first,last)) ){
             flag=false;
             break;
            }
            i++;
            j--;

        }
//
        if(flag==false)
        System.out.println(" not palindrome");
        else{
            System.out.println("palindrome");

        }






        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
      
    }
}