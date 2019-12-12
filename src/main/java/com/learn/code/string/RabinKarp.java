package com.learn.code.string;

/**
 * Created by Chopra on 21/09/19.
 */
public class RabinKarp {
    // O((n-m+1)*m)


    public boolean search(String pattern, String text){
        if(pattern.length()>text.length())
            return false;

        int d = 256;
        int q = 101;
        int h = 1;

        for(int i=0;i<pattern.length();i++){
            h = (h*d)%q;
        }

        int p,t;
        p=t=0;
        for(int i=0;i<pattern.length();i++){
            p = (p*d+pattern.charAt(i))%q;
            t = (t*d+text.charAt(i))%q;
        }

        for(int i=0;i<=text.length() - pattern.length();i++){
            if(p==t){
                boolean flag = true;
                for(int j=0;j<pattern.length();j++){
                    if(pattern.charAt(j)!=text.charAt(i+j)){
                        flag= false;
                        break;
                    }
                }
                if(flag)
                    return true;
            }

            if (i+pattern.length()<text.length()) {
                t = ((d * (t - (h * text.charAt(i)))) + text.charAt(i + pattern.length()))%q;
                if (t<0)
                    t=t+q;
            }


        }
        return false;
    }


    public static void main(String[] args) {
        RabinKarp rabinKarp = new RabinKarp();
        System.out.println(rabinKarp.search("AABA","CABAA"));
    }
}
