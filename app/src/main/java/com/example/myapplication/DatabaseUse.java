package com.example.myapplication;

import android.content.Context;

public class DatabaseUse {

    DatabaseHelper mDb;

    void firstInsert(Context context) {
        //DB 부분
        mDb = new DatabaseHelper(context);

        // disease insert
        mDb.insertDisease("만성폐쇄성질환", "카페인", "벤토린에보할러");
        mDb.insertDisease("만성폐쇄성질환", "카페인", "부벤톨이지할러");
        mDb.insertDisease("만성폐쇄성질환", "자몽", "심비코트터부헬러");
        mDb.insertDisease("만성폐쇄성질환", "자몽", "듀오레스피스피로맥스");

        mDb.insertDisease("결핵", "가다랑어", "신풍이소니아짓정");
        mDb.insertDisease("결핵", "가다랑어", "유유아이나정");
        mDb.insertDisease("결핵", "가다랑어", "유한짓정");
        mDb.insertDisease("결핵", "가다랑어", "코러스이소니코틴산히드라짓정");

        mDb.insertDisease("결핵", "참치", "신풍이소니아짓정");
        mDb.insertDisease("결핵", "참치", "유유아이나정");
        mDb.insertDisease("결핵", "참치", "유한짓정");
        mDb.insertDisease("결핵", "참치", "코러스이소니코틴산히드라짓정");

        mDb.insertDisease("결핵", "알코올", "마이암부톨제피정400mg");
        mDb.insertDisease("결핵", "알코올", "코러스염산에탐부톨정");
        mDb.insertDisease("결핵", "알코올", "탐부톨정");
        mDb.insertDisease("결핵", "알코올", "튜톨정");
        mDb.insertDisease("결핵", "알코올", "리네졸린정");

        mDb.insertDisease("장염", "철분보충제", "베루본에스정");

        mDb.insertDisease("지방간", "마그네슘보충제", "바이덱스EC서방형캡슐");
        mDb.insertDisease("지방간", "알코올", "바이덱스EC서방형캡슐");

        mDb.insertDisease("크론병", "알코올", "살로팔크포말");
        mDb.insertDisease("크론병", "알코올", "아사콜디알정400mg");
        mDb.insertDisease("크론병", "알코올", "살로팔크500과립");
        mDb.insertDisease("크론병", "알코올", "펜타사서방과립1g");
        mDb.insertDisease("크론병", "알코올", "메자반트엑스엘장용정1200mg");

        mDb.insertDisease("크론병", "알코올", "아이비에프정");
        mDb.insertDisease("크론병", "알코올", "나르펜정");
        mDb.insertDisease("크론병", "알코올", "로제펜정");
        mDb.insertDisease("크론병", "알코올", "이바펜정");
        mDb.insertDisease("크론병", "알코올", "지엘이부프로펜정");

        mDb.insertDisease("크론병", "카페인", "아이비에프정");
        mDb.insertDisease("크론병", "카페인", "나르펜정");
        mDb.insertDisease("크론병", "카페인", "로제펜정");
        mDb.insertDisease("크론병", "카페인", "이바펜정");
        mDb.insertDisease("크론병", "카페인", "지엘이부프로펜정");

        mDb.insertDisease("크론병", "생강", "아이비에프정");
        mDb.insertDisease("크론병", "생강", "나르펜정");
        mDb.insertDisease("크론병", "생강", "로제펜정");
        mDb.insertDisease("크론병", "생강", "이바펜정");
        mDb.insertDisease("크론병", "생강", "지엘이부프로펜정");

        mDb.insertDisease("크론병", "마늘", "아이비에프정");
        mDb.insertDisease("크론병", "마늘", "나르펜정");
        mDb.insertDisease("크론병", "마늘", "로제펜정");
        mDb.insertDisease("크론병", "마늘", "이바펜정");
        mDb.insertDisease("크론병", "마늘", "지엘이부프로펜정");

        mDb.insertDisease("크론병", "카페인", "경방시프로플록사신염산염수화물정");
        mDb.insertDisease("크론병", "오렌지", "경방시프로플록사신염산염수화물정");

        mDb.insertDisease("간염", "알코올", "비키라정");

        mDb.insertDisease("고혈압", "감초캔디", "동성캡토프릴25mg정(수출용)");
        mDb.insertDisease("고혈압", "칼륨", "동성캡토프릴25mg정(수출용)");

        mDb.insertDisease("고혈압", "칼륨", "나라프릴정");
        mDb.insertDisease("고혈압", "칼륨", "나프릴정");
        mDb.insertDisease("고혈압", "칼륨", "레날라핀정");
        mDb.insertDisease("고혈압", "칼륨", "메라니텍정");
        mDb.insertDisease("고혈압", "칼륨", "에나프릴정");

        mDb.insertDisease("고혈압", "칼륨", "리시탄플러스정10mg/12.5mg");
        mDb.insertDisease("고혈압", "알코올", "리시탄플러스정10mg/12.5mg");

        mDb.insertDisease("고혈압", "알코올", "국제아테놀롤정");
        mDb.insertDisease("고혈압", "알코올", "아소틴정");
        mDb.insertDisease("고혈압", "알코올", "아테놀정");
        mDb.insertDisease("고혈압", "알코올", "테나롤정");

        mDb.insertDisease("고혈압", "알코올", "로지맥스서방정");
        mDb.insertDisease("고혈압", "알코올", "로벨리토정150/10mg");

        mDb.insertDisease("고혈압", "자몽", "로지맥스서방정");
        mDb.insertDisease("고혈압", "자몽", "로벨리토정150/10mg");

        mDb.insertDisease("고혈압", "알코올", "라이테민정");
        mDb.insertDisease("고혈압", "알코올", "아놀라정");
        mDb.insertDisease("고혈압", "알코올", "고려칸데사르탄플러스정");
        mDb.insertDisease("고혈압", "알코올", "아나탄플러스정");

        mDb.insertDisease("고혈압", "알코올", "나트릭스서방정");
        mDb.insertDisease("고혈압", "알코올", "후루덱스서방정");

        mDb.insertDisease("고혈압", "아스파라거스", "나트릭스서방정");
        mDb.insertDisease("고혈압", "아스파라거스", "후루덱스서방정");

        mDb.insertDisease("고혈압", "민들레차", "나트릭스서방정");
        mDb.insertDisease("고혈압", "민들레차", "후루덱스서방정");

        mDb.insertDisease("고혈압", "칼슘보충제", "나트릭스서방정");
        mDb.insertDisease("고혈압", "칼슘보충제", "후루덱스서방정");

        mDb.insertDisease("고혈압", "녹차", "나트릭스서방정");
        mDb.insertDisease("고혈압", "녹차", "후루덱스서방정");

        mDb.insertDisease("협심증", "알코올", "임듈지속정30mg");
        mDb.insertDisease("협심증", "알코올", "엘로톤서방캡슐50mg");
        mDb.insertDisease("협심증", "알코올", "인데놀정10mg");
        mDb.insertDisease("협심증", "알코올", "부광켈론정");

        mDb.insertDisease("협심증", "알코올", "일성이솦틴서방정180mg");
        mDb.insertDisease("협심증", "알코올", "아달라트연질캡슐5mg");

        mDb.insertDisease("협심증", "자몽", "일성이솦틴서방정180mg");
        mDb.insertDisease("협심증", "자몽", "아달라트연질캡슐5mg");

        mDb.insertDisease("관상동맥 우회술", "비타민E", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "비타민E", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "비타민E", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "비타민E", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "비타민E", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "비타민A", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "비타민A", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "비타민A", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "비타민A", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "비타민A", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "녹차", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "녹차", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "녹차", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "녹차", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "녹차", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "마늘", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "마늘", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "마늘", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "마늘", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "마늘", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "생강", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "생강", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "생강", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "생강", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "생강", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "인삼", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "인삼", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "인삼", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "인삼", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "인삼", "휴로픽스정");

        mDb.insertDisease("관상동맥 우회술", "은행엽", "케라픽스정");
        mDb.insertDisease("관상동맥 우회술", "은행엽", "그로리정");
        mDb.insertDisease("관상동맥 우회술", "은행엽", "로피렐정");
        mDb.insertDisease("관상동맥 우회술", "은행엽", "세레나데정");
        mDb.insertDisease("관상동맥 우회술", "은행엽", "휴로픽스정");

        mDb.insertDisease("심근경색증", "자몽", "비티오니페디핀정20mg(수출용)");
        mDb.insertDisease("심근경색증", "알코올", "비티오니페디핀정20mg(수출용)");

        mDb.insertDisease("심근경색증", "비타민E", "큐로벡스정");
        mDb.insertDisease("심근경색증", "비타민E", "네오빅스정");
        mDb.insertDisease("심근경색증", "비타민E", "로피렐정");
        mDb.insertDisease("심근경색증", "비타민E", "세레나데정");


        mDb.insertDisease("심근경색증", "비타민A", "큐로벡스정");
        mDb.insertDisease("심근경색증", "비타민A", "네오빅스정");
        mDb.insertDisease("심근경색증", "비타민A", "로피렐정");
        mDb.insertDisease("심근경색증", "비타민A", "세레나데정");

        mDb.insertDisease("심근경색증", "녹차", "큐로벡스정");
        mDb.insertDisease("심근경색증", "녹차", "네오빅스정");
        mDb.insertDisease("심근경색증", "녹차", "로피렐정");
        mDb.insertDisease("심근경색증", "녹차", "세레나데정");

        mDb.insertDisease("심근경색증", "마늘", "큐로벡스정");
        mDb.insertDisease("심근경색증", "마늘", "네오빅스정");
        mDb.insertDisease("심근경색증", "마늘", "로피렐정");
        mDb.insertDisease("심근경색증", "마늘", "세레나데정");

        mDb.insertDisease("심근경색증", "생강", "큐로벡스정");
        mDb.insertDisease("심근경색증", "생강", "네오빅스정");
        mDb.insertDisease("심근경색증", "생강", "로피렐정");
        mDb.insertDisease("심근경색증", "생강", "세레나데정");

        mDb.insertDisease("심근경색증", "인삼", "큐로벡스정");
        mDb.insertDisease("심근경색증", "인삼", "네오빅스정");
        mDb.insertDisease("심근경색증", "인삼", "로피렐정");
        mDb.insertDisease("심근경색증", "인삼", "세레나데정");

        mDb.insertDisease("심근경색증", "은행엽", "큐로벡스정");
        mDb.insertDisease("심근경색증", "은행엽", "네오빅스정");
        mDb.insertDisease("심근경색증", "은행엽", "로피렐정");
        mDb.insertDisease("심근경색증", "은행엽", "세레나데정");

        mDb.insertDisease("당뇨병", "알코올", "노보래피드주100단위/mL");
        mDb.insertDisease("당뇨병", "알코올", "란투스주바이알");
        mDb.insertDisease("당뇨병", "알코올", "애피드라주바이알");
        mDb.insertDisease("당뇨병", "알코올", "휴물린알주100단위");
        mDb.insertDisease("당뇨병", "알코올", "레버미어플렉스펜주100단위/mL");
        mDb.insertDisease("당뇨병", "알코올", "노보넘정0.5mg");
        mDb.insertDisease("당뇨병", "알코올", "빅토자펜주6mg/mL");

        mDb.insertDisease("당뇨병", "알코올", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "알코올", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "알코올", "메로민정");
        mDb.insertDisease("당뇨병", "알코올", "케이메트서방정");
        mDb.insertDisease("당뇨병", "알코올", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "알코올", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "알코올", "글루코바이정100mg");

        mDb.insertDisease("당뇨병", "감자", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "감자", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "감자", "메로민정");
        mDb.insertDisease("당뇨병", "감자", "케이메트서방정");
        mDb.insertDisease("당뇨병", "감자", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "감자", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "감자", "글루코바이정100mg");

        mDb.insertDisease("당뇨병", "알로에", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "알로에", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "알로에", "메로민정");
        mDb.insertDisease("당뇨병", "알로에", "케이메트서방정");
        mDb.insertDisease("당뇨병", "알로에", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "알로에", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "알로에", "글루코바이정100mg");

        mDb.insertDisease("당뇨병", "녹차", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "녹차", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "녹차", "메로민정");
        mDb.insertDisease("당뇨병", "녹차", "케이메트서방정");
        mDb.insertDisease("당뇨병", "녹차", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "녹차", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "녹차", "글루코바이정100mg");

        mDb.insertDisease("당뇨병", "아스파라거스", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "아스파라거스", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "아스파라거스", "메로민정");
        mDb.insertDisease("당뇨병", "아스파라거스", "케이메트서방정");
        mDb.insertDisease("당뇨병", "아스파라거스", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "아스파라거스", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "아스파라거스", "글루코바이정100mg");

        mDb.insertDisease("당뇨병", "민들레", "다이아벡스엑스알서방정");
        mDb.insertDisease("당뇨병", "민들레", "글루코파지엑스알서방정");
        mDb.insertDisease("당뇨병", "민들레", "메로민정");
        mDb.insertDisease("당뇨병", "민들레", "케이메트서방정");
        mDb.insertDisease("당뇨병", "민들레", "파스틱메트정120/500mg");
        mDb.insertDisease("당뇨병", "민들레", "액토스릴정30/2mg");
        mDb.insertDisease("당뇨병", "민들레", "글루코바이정100mg");

        mDb.insertDisease("비만", "알코올", "대화노브제정");
        mDb.insertDisease("비만", "알코올", "디에타민정");
        mDb.insertDisease("비만", "알코올", "레티스정");
        mDb.insertDisease("비만", "알코올", "비엠진정");
        mDb.insertDisease("비만", "알코올", "아디펙스정");
        mDb.insertDisease("비만", "알코올", "페스틴정");
        mDb.insertDisease("비만", "알코올", "틴틴정");

        mDb.insertDisease("빈혈", "알코올", "헤모비스큐연질캡슐");
        mDb.insertDisease("빈혈", "알코올", "헤모스쿨큐연질캡슐");
        mDb.insertDisease("빈혈", "알코올", "헤모에이스큐연질캡슐");

        mDb.insertDisease("빈혈", "탄산염제산제", "헤모비스큐연질캡슐");
        mDb.insertDisease("빈혈", "탄산염제산제", "헤모스쿨큐연질캡슐");
        mDb.insertDisease("빈혈", "탄산염제산제", "헤모에이스큐연질캡슐");

        mDb.insertDisease("빈혈", "칼슘인", "헤모비스큐연질캡슐");
        mDb.insertDisease("빈혈", "칼슘인", "헤모스쿨큐연질캡슐");
        mDb.insertDisease("빈혈", "칼슘인", "헤모에이스큐연질캡슐");

        mDb.insertDisease("빈혈", "아연", "헤모비스큐연질캡슐");
        mDb.insertDisease("빈혈", "아연", "헤모스쿨큐연질캡슐");
        mDb.insertDisease("빈혈", "아연", "헤모에이스큐연질캡슐");

        mDb.insertDisease("빈혈", "구리", "헤모비스큐연질캡슐");
        mDb.insertDisease("빈혈", "구리", "헤모스쿨큐연질캡슐");
        mDb.insertDisease("빈혈", "구리", "헤모에이스큐연질캡슐");

        mDb.insertDisease("백혈병", "알코올", "글리벡필름코팅정");
        mDb.insertDisease("백혈병", "알코올", "글로팁정");
        mDb.insertDisease("백혈병", "알코올", "글리마정");
        mDb.insertDisease("백혈병", "알코올", "류코벡정");
        mDb.insertDisease("백혈병", "알코올", "제이티닙정");

        mDb.insertDisease("백혈병", "자몽", "글리벡필름코팅정");
        mDb.insertDisease("백혈병", "자몽", "글로팁정");
        mDb.insertDisease("백혈병", "자몽", "글리마정");
        mDb.insertDisease("백혈병", "자몽", "류코벡정");
        mDb.insertDisease("백혈병", "자몽", "제이티닙정");

        mDb.insertDisease("백혈병", "자몽", "타시그나캡슐");

        mDb.insertDisease("뇌전증", "자몽", "대화카르바마제핀정");
        mDb.insertDisease("뇌전증", "알코올", "대화카르바마제핀정");

        mDb.insertDisease("뇌전증", "알코올", "명인페니토인정100mg");
        mDb.insertDisease("뇌전증", "알코올", "환인히단토인정");
        mDb.insertDisease("뇌전증", "알코올", "사브릴산50mg");

        mDb.insertDisease("뇌전증", "칼슘", "명인페니토인정100mg");
        mDb.insertDisease("뇌전증", "칼슘", "환인히단토인정");
        mDb.insertDisease("뇌전증", "칼슘", "사브릴산50mg");

        mDb.insertDisease("뇌전증", "마그네슘", "명인페니토인정100mg");
        mDb.insertDisease("뇌전증", "마그네슘", "환인히단토인정");
        mDb.insertDisease("뇌전증", "마그네슘", "사브릴산50mg");

        mDb.insertDisease("뇌전증", "알코올", "데파킨크로노정300mg");
        mDb.insertDisease("뇌전증", "알코올", "바렙톨서방정");
        mDb.insertDisease("뇌전증", "알코올", "오르필서방정300mg");
        mDb.insertDisease("뇌전증", "알코올", "엑세그란정");
        mDb.insertDisease("뇌전증", "알코올", "라모스탈정100mg");
        mDb.insertDisease("뇌전증", "알코올", "가바토파정100mg");
        mDb.insertDisease("뇌전증", "알코올", "자론티연질캡슐");

        mDb.insertDisease("뇌경색", "자몽", "실로탄정100mg");
        mDb.insertDisease("뇌경색", "자몽", "실로벨서방캡슐100mg");
        mDb.insertDisease("뇌경색", "자몽", "로사졸정100mg");
        mDb.insertDisease("뇌경색", "자몽", "로스탈정100mg");
        mDb.insertDisease("뇌경색", "자몽", "실로브이정");
        mDb.insertDisease("뇌경색", "자몽", "프레탈정100mg");
        mDb.insertDisease("뇌경색", "자몽", "씨타졸정100mg");

        mDb.insertDisease("뇌수막염", "알코올", "프라임메트로니다졸정500mg(수출용)");
        mDb.insertDisease("뇌수막염", "알코올", "뉴스타틴티에스정40/5/10mg");
        mDb.insertDisease("뇌수막염", "알코올", "듀오웰에이정40/5/10mg");
        mDb.insertDisease("뇌수막염", "알코올", "텔로스톱플러스정40/5/10mg");
        mDb.insertDisease("뇌수막염", "알코올", "트레블정40/5/10mg");

        mDb.insertDisease("골다공증", "알코올", "에비스타정60mg");
        mDb.insertDisease("골다공증", "알코올", "뉴아베스타정");
        mDb.insertDisease("골다공증", "알코올", "라록스펜정");
        mDb.insertDisease("골다공증", "알코올", "라록스타정");
        mDb.insertDisease("골다공증", "알코올", "리드론플러스정");
        mDb.insertDisease("골다공증", "알코올", "뉴토넬플러스정");

        mDb.insertDisease("통풍", "자몽", "콜킨정");
        mDb.insertDisease("통풍", "자몽", "콜키신");
        mDb.insertDisease("통풍", "자몽", "콜키닌정");

        mDb.insertDisease("통풍", "알코올", "콜킨정");
        mDb.insertDisease("통풍", "알코올", "콜키신");
        mDb.insertDisease("통풍", "알코올", "콜키닌정");

        mDb.insertDisease("방광염", "알코올", "박토랄정");
        mDb.insertDisease("방광염", "알코올", "셉트린정");

        mDb.insertDisease("방광염", "제산제", "박토랄정");
        mDb.insertDisease("방광염", "제산제", "셉트린정");

        mDb.insertDisease("방광염", "칼륨보충제", "박토랄정");
        mDb.insertDisease("방광염", "칼륨보충제", "셉트린정");

        mDb.insertDisease("방광염", "마그네슘", "씨프로바이정250mg");
        mDb.insertDisease("방광염", "철분", "씨프로바이정250mg");
        mDb.insertDisease("방광염", "카페인", "씨프로바이정250mg");
        mDb.insertDisease("방광염", "아연", "씨프로바이정250mg");
        mDb.insertDisease("방광염", "칼슘", "씨프로바이정250mg");

        mDb.insertDisease("방광염", "카페인", "제일크라비트정");

        mDb.insertDisease("요실금", "알코올", "베시케어정5mg");
        mDb.insertDisease("요실금", "알코올", "솔리신정10mg");
        mDb.insertDisease("요실금", "알코올", "토비애즈서방정4mg");

        mDb.insertDisease("요실금", "알코올", "동화디트로판정");
        mDb.insertDisease("요실금", "알코올", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "알코올", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "알코올", "디트루시톨정1mg");

        mDb.insertDisease("요실금", "마늘", "동화디트로판정");
        mDb.insertDisease("요실금", "마늘", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "마늘", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "마늘", "디트루시톨정1mg");

        mDb.insertDisease("요실금", "멜라토닌", "동화디트로판정");
        mDb.insertDisease("요실금", "멜라토닌", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "멜라토닌", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "멜라토닌", "디트루시톨정1mg");

        mDb.insertDisease("요실금", "녹차", "동화디트로판정");
        mDb.insertDisease("요실금", "녹차", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "녹차", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "녹차", "디트루시톨정1mg");

        mDb.insertDisease("요실금", "아스파라거스", "동화디트로판정");
        mDb.insertDisease("요실금", "아스파라거스", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "아스파라거스", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "아스파라거스", "디트루시톨정1mg");


        mDb.insertDisease("요실금", "민들레", "동화디트로판정");
        mDb.insertDisease("요실금", "민들레", "라이리넬오로스서방정10mg");
        mDb.insertDisease("요실금", "민들레", "디트로딘에스알캡슐2mg");
        mDb.insertDisease("요실금", "민들레", "디트루시톨정1mg");

        mDb.insertDisease("알레르기비염", "알코올", "지르텍정");
        mDb.insertDisease("알레르기비염", "알코올", "씨잘정");
        mDb.insertDisease("알레르기비염", "알코올", "에리우스정");
        mDb.insertDisease("알레르기비염", "알코올", "클라리틴시럽");
        mDb.insertDisease("알레르기비염", "알코올", "나리스타에스점비액");

        mDb.insertDisease("알레르기비염", "사과", "알레그라정");
        mDb.insertDisease("알레르기비염", "사과", "알레그라디정");

        mDb.insertDisease("알레르기비염", "오렌지", "알레그라정");
        mDb.insertDisease("알레르기비염", "오렌지", "알레그라디정");

        mDb.insertDisease("알레르기비염", "자몽", "알레그라정");
        mDb.insertDisease("알레르기비염", "자몽", "알레그라디정");

        mDb.insertDisease("알레르기비염", "알코올", "알레그라정");
        mDb.insertDisease("알레르기비염", "알코올", "알레그라디정");

        mDb.insertDisease("알레르기비염", "카페인", "액티피드정");
        mDb.insertDisease("알레르기비염", "카페인", "그린노즈캡슐");
        mDb.insertDisease("알레르기비염", "카페인", "코싹엘정");

        mDb.insertDisease("알레르기비염", "알코올", "액티피드정");
        mDb.insertDisease("알레르기비염", "알코올", "그린노즈캡슐");
        mDb.insertDisease("알레르기비염", "알코올", "코싹엘정");

        mDb.insertDisease("알레르기비염", "카페인", "리노에바스텔캡슐");

        mDb.insertDisease("알레르기비염", "자몽", "나리타점비액");
        mDb.insertDisease("알레르기비염", "자몽", "데소나비액");

        mDb.insertDisease("중이염", "알코올", "뉴클래토정250mg");

        mDb.insertDisease("우울증", "알코올", "렉시프람정5mg");
        mDb.insertDisease("우울증", "알코올", "루복스정100mg");
        mDb.insertDisease("우울증", "알코올", "플루민캡슐10mg");
        mDb.insertDisease("우울증", "알코올", "센시발정10mg");

        mDb.insertDisease("우울증", "카페인", "렉시프람정5mg");
        mDb.insertDisease("우울증", "카페인", "루복스정100mg");
        mDb.insertDisease("우울증", "카페인", "플루민캡슐10mg");
        mDb.insertDisease("우울증", "카페인", "센시발정10mg");

        mDb.insertDisease("우울증", "카페인", "명인이미프라민염산염정");
        mDb.insertDisease("우울증", "카페인", "환인이미프라민염산염정25mg");

        mDb.insertDisease("불면증", "알코올", "조피스타정1mg");
        mDb.insertDisease("불면증", "알코올", "단자민정");
        mDb.insertDisease("불면증", "알코올", "쿨드림연질캡슐");
        mDb.insertDisease("불면증", "알코올", "슬리펠정");

        mDb.insertDisease("불면증", "알코올", "종근당주석산졸피뎀정10mg");
        mDb.insertDisease("불면증", "알코올", "산도스졸피뎀정10mg");
        mDb.insertDisease("불면증", "알코올", "스틸렉스정10mg");
        mDb.insertDisease("불면증", "알코올", "파마주석산졸피뎀정");
        mDb.insertDisease("불면증", "알코올", "도미졸정10mg");

        mDb.insertDisease("불면증", "카페인", "종근당주석산졸피뎀정10mg");
        mDb.insertDisease("불면증", "카페인", "산도스졸피뎀정10mg");
        mDb.insertDisease("불면증", "카페인", "스틸렉스정10mg");
        mDb.insertDisease("불면증", "카페인", "파마주석산졸피뎀정");
        mDb.insertDisease("불면증", "카페인", "도미졸정10mg");

        mDb.insertDisease("불면증", "니코틴", "종근당주석산졸피뎀정10mg");
        mDb.insertDisease("불면증", "니코틴", "산도스졸피뎀정10mg");
        mDb.insertDisease("불면증", "니코틴", "스틸렉스정10mg");
        mDb.insertDisease("불면증", "니코틴", "파마주석산졸피뎀정");
        mDb.insertDisease("불면증", "니코틴", "도미졸정10mg");

        mDb.insertDisease("불면증", "알코올", "졸민정0.125mg");
        mDb.insertDisease("불면증", "알코올", "트리람정0.125mg");
        mDb.insertDisease("불면증", "알코올", "할시온정0.125mg");

        mDb.insertDisease("불면증", "카페인", "졸민정0.125mg");
        mDb.insertDisease("불면증", "카페인", "트리람정0.125mg");
        mDb.insertDisease("불면증", "카페인", "할시온정0.125mg");

        mDb.insertDisease("불면증", "니코틴", "졸민정0.125mg");
        mDb.insertDisease("불면증", "니코틴", "트리람정0.125mg");
        mDb.insertDisease("불면증", "니코틴", "할시온정0.125mg");

        mDb.insertDisease("불면증", "자몽", "졸민정0.125mg");
        mDb.insertDisease("불면증", "자몽", "트리람정0.125mg");
        mDb.insertDisease("불면증", "자몽", "할시온정0.125mg");

        mDb.insertDisease("치매", "알코올", "피엠에스리스페리돈정0.5mg");
        mDb.insertDisease("치매", "알코올", "리스돈정0.5mg");
        mDb.insertDisease("치매", "알코올", "아빌리파이오디정10mg");

        mDb.insertDisease("공황장애", "카페인", "명인이미프라민염산염정");
        mDb.insertDisease("공황장애", "카페인", "환인이미프라민염산염정25mg");

        mDb.insertDisease("공황장애", "알코올", "명문알프라졸람정0.25mg");
        mDb.insertDisease("공황장애", "알코올", "로라반정0.5mg");
        mDb.insertDisease("공황장애", "알코올", "스리반정0.5mg");
        mDb.insertDisease("공황장애", "알코올", "리보트릴정");

        mDb.insertDisease("공황장애", "카페인", "명문알프라졸람정0.25mg");
        mDb.insertDisease("공황장애", "카페인", "로라반정0.5mg");
        mDb.insertDisease("공황장애", "카페인", "스리반정0.5mg");
        mDb.insertDisease("공황장애", "카페인", "리보트릴정");

        mDb.insertDisease("공황장애", "알코올", "바로인에이연질캡슐");
        mDb.insertDisease("공황장애", "알코올", "발핀연질캡슐500mg");

        mDb.insertDisease("조현병", "알코올", "네오마찐정100mg");
        mDb.insertDisease("조현병", "알코올", "명인페르페나진정4mg");
        mDb.insertDisease("조현병", "알코올", "리단정");
        mDb.insertDisease("조현병", "알코올", "클로자릴정25mg");
        mDb.insertDisease("조현병", "알코올", "환인탄산리튬정");
        mDb.insertDisease("조현병", "알코올", "명인탄산리튬정");

        mDb.insertDisease("조현병", "카페인", "네오마찐정100mg");
        mDb.insertDisease("조현병", "카페인", "명인페르페나진정4mg");
        mDb.insertDisease("조현병", "카페인", "리단정");
        mDb.insertDisease("조현병", "카페인", "클로자릴정25mg");
        mDb.insertDisease("조현병", "카페인", "환인탄산리튬정");
        mDb.insertDisease("조현병", "카페인", "명인탄산리튬정");

        mDb.insertDisease("조현병", "알코올", "페리돌정1.5mg");
        mDb.insertDisease("조현병", "알코올", "자이프렉사정2.5mg");
        mDb.insertDisease("조현병", "알코올", "모반정10mg");
        mDb.insertDisease("조현병", "알코올", "리스페달정0.5mg");

        mDb.insertDisease("조울증", "카페인", "리단정");
        mDb.insertDisease("조울증", "카페인", "명인탄산리튬정");
        mDb.insertDisease("조울증", "카페인", "환인탄산리튬정");

        mDb.insertDisease("조울증", "알코올", "리단정");
        mDb.insertDisease("조울증", "알코올", "명인탄산리튬정");
        mDb.insertDisease("조울증", "알코올", "환인탄산리튬정");

        mDb.insertDisease("ADHD", "알코올", "메디키넷리타드캡슐10mg");
        mDb.insertDisease("ADHD", "알코올", "페니드정5mg");
        mDb.insertDisease("ADHD", "알코올", "콘서타OROS서방정27mg");
        mDb.insertDisease("ADHD", "알코올", "메타데이트CD서방캡슐10mg");
        mDb.insertDisease("ADHD", "알코올", "비스펜틴조절방출캡슐10mg");

        mDb.insertDisease("ADHD", "카페인", "메디키넷리타드캡슐10mg");
        mDb.insertDisease("ADHD", "카페인", "페니드정5mg");
        mDb.insertDisease("ADHD", "카페인", "콘서타OROS서방정27mg");
        mDb.insertDisease("ADHD", "카페인", "메타데이트CD서방캡슐10mg");
        mDb.insertDisease("ADHD", "카페인", "비스펜틴조절방출캡슐10mg");

        mDb.insertDisease("외상 후 스트레스 장애", "알코올", "환인파록세틴정20mg");
        mDb.insertDisease("외상 후 스트레스 장애", "알코올", "산도스설트랄린정");
        mDb.insertDisease("외상 후 스트레스 장애", "알코올", "졸로푸트정50mg");
        mDb.insertDisease("외상 후 스트레스 장애", "알코올", "파마설트랄린정50mg");
        mDb.insertDisease("외상 후 스트레스 장애", "알코올", "영진설트랄린정50mg");

        mDb.insertDisease("외상 후 스트레스 장애", "카페인", "환인파록세틴정20mg");
        mDb.insertDisease("외상 후 스트레스 장애", "카페인", "산도스설트랄린정");
        mDb.insertDisease("외상 후 스트레스 장애", "카페인", "졸로푸트정50mg");
        mDb.insertDisease("외상 후 스트레스 장애", "카페인", "파마설트랄린정50mg");
        mDb.insertDisease("외상 후 스트레스 장애", "카페인", "영진설트랄린정50mg");

        mDb.insertDisease("방광암", "알코올", "보령에피루비신염산염주(25mL)");
        mDb.insertDisease("방광암", "알코올", "에피진주50mg");
        mDb.insertDisease("방광암", "알코올", "아드로신주");
        mDb.insertDisease("방광암", "알코올", "도비신주10mg");

        mDb.insertDisease("패혈증", "알코올", "후라질주(100mL/병(PP))");
        mDb.insertDisease("패혈증", "알코올", "메트리날주");
        mDb.insertDisease("패혈증", "알코올", "트리젤주(100mL/백)");
        mDb.insertDisease("패혈증", "알코올", "이노엔메트로니다졸주0.5g");


        // allergy insert
        mDb.insertAllergy("난류");
        mDb.insertAllergy("우유");
        mDb.insertAllergy("메밀");
        mDb.insertAllergy("땅콩");
        mDb.insertAllergy("대두");
        mDb.insertAllergy("밀");
        mDb.insertAllergy("고등어");
        mDb.insertAllergy("게");
        mDb.insertAllergy("새우");
        mDb.insertAllergy("돼지고기");
        mDb.insertAllergy("복숭아");
        mDb.insertAllergy("토마토");
        mDb.insertAllergy("아황산류");
        mDb.insertAllergy("호두");
        mDb.insertAllergy("닭고기");
        mDb.insertAllergy("소고기");
        mDb.insertAllergy("오징어");
        mDb.insertAllergy("잣");
        mDb.insertAllergy("조개류");

        mDb.close();
    }

}
