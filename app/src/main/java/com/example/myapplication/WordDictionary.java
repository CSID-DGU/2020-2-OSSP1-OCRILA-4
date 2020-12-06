/*
package com.example.myapplication;

import info.debatty.java.stringsimilarity.*;

public class WordDictionary {

    WeightedLevenshtein wl = new WeightedLevenshtein(new CharacterSubstitutionInterface() {
        public double cost(char c1, char c2) {

            MetricLCS lcs = new MetricLCS();

            // The cost for substituting 't' and 'r' is considered
            // smaller as these 2 are located next to each other
            // on a keyboard
            if (c1 == '제' && c2 == '지') {
                return 0.5;
            }
            else if (c1 == '황' && c2 == '왕') {
                return 0.5;
            }
            else if (c1 == '산' && c2 == '샌') {
                return 0.5;
            }
            else if (c1 == '우' && c2 == '운') {
                return 0.5;
            }
            else if (c1 == '물' && c2 == '둘') {
                return 0.5;
            }
            else if (c1 == '찹' && c2 == '첩') {
                return 0.5;
            }
            else if (c1 == '베' && c2 == '세') {
                return 0.5;
            }
            else if (c1 == '유' && c2 == '요') {
                return 0.5;
            }
            else if (c1 == '덱' && c2 == '덕') {
                return 0.5;
            }
            else if (c1 == '엑' && c2 == '역') {
                return 0.5;
            }
            else if (c1 == '새' && c2 == '사') {
                return 0.5;
            }
            else if (c1 == '스' && c2 == '소') {
                return 0.5;
            }
            else if (c1 == '새' && c2 == '시') {
                return 0.5;
            }
            else if (c1 == '야' && c2 == '여') {
                return 0.5;
            }
            else if (c1 == '채' && c2 == '제') {
                return 0.5;
            }
            else if (c1 == '육' && c2 == '유') {
                return 0.5;
            }
            else if (c1 == '재' && c2 == '지') {
                return 0.5;
            }
            else if (c1 == '제' && c2 == '재') {
                return 0.5;
            }
            else if (c1 == '향' && c2 == '항') {
                return 0.5;
            }
            else if (c1 == '개' && c2 == '기') {
                return 0.5;
            }
            else if (c1 == '매' && c2 == '내') {
                return 0.5;
            }
            else if (c1 == '농' && c2 == '공') {
                return 0.5;
            }
            else if (c1 == '합' && c2 == '한') {
                return 0.5;
            }
            else if (c1 == '향' && c2 == '행') {
                return 0.5;
            }
            else if (c1 == '재' && c2 == '대') {
                return 0.5;
            }
            else if (c1 == '베' && c2 == '배') {
                return 0.5;
            }
            else if (c1 == '조' && c2 == '지') {
                return 0.5;
            }
            else if (c1 == '인' && c2 == '이') {
                return 0.5;
            }
            else if (c1 == '산' && c2 == '서') {
                return 0.5;
            }
            else if (c1 == '참' && c2 == '청') {
                return 0.5;
            }
            else if (c1 == '치' && c2 == '처') {
                return 0.5;
            }
            else if (c1 == '함' && c2 == '험') {
                return 0.5;
            }
            else if (c1 == '량' && c2 == '영') {
                return 0.5;
            }
            else if (c1 == '탕' && c2 == '동') {
                return 0.5;
            }
            else if (c1 == '정' && c2 == '장') {
                return 0.5;
            }
            else if (c1 == '탕' && c2 == '명') {
                return 0.5;
            }
            else if (c1 == '이' && c2 == '아') {
                return 0.5;
            }


            // For most cases, the cost of substituting 2 characters
            // is 1.0
            return 1.0;
        }
    });
}
*/