package com.learn.code.string;

/**
 * Created by Chopra on 21/09/19.
 */
public class KMP {
    // O(n+m)

    void computeLps(String pattern,int[] lps){
        int n = pattern.length();
        lps[0]=0;
        int i=1;

        int k=0;

        while (i<n){
            if (pattern.charAt(k) == pattern.charAt(i)){
                k++;
                lps[i]=k;
                i++;
            }
            else{
                if (k!=0){
                    k=lps[k-1];
                }
                else{
                    lps[i]=k;
                    i++;
                }
            }

        }
    }

    void search(String text, String pattern){
        int[] lps = new int[pattern.length()];
        computeLps(pattern,lps);

        int i,j;
        i=j=0;

        while (i<text.length()){
            if (text.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }
            if (j==pattern.length()){
                System.out.println("pattern found");
                return;
            }

            if (i<text.length() && text.charAt(i)!=pattern.charAt(j)){
                if (j!=0){
                    j = lps[j-1];
                }
                else
                    i=i+1;
            }
        }
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        kmp.search("ABABDABACDABABCABAB","ABABCABAB");
    }

}
