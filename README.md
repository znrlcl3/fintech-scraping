# 테크 금융 데이터 스크래핑

## 기초 환경 구축 및 상세 페이지 추출 성공
- **타겟 사이트**: 네이버 금융 (삼성전자 상세 페이지)
- **주요 기술**: Java, Jsoup, Maven, Git

### 오늘 배운 포인트
1. **Targeting**: 주소창의 검색 URL 대신, 종목 코드가 포함된 '진짜 상세 주소'를 사용해 안정성 확보.
2. **Spoofing**: `User-Agent`와 `Referer` 헤더를 설정하여 브라우저의 요청으로 위장, 보안 차단(HTTP 500) 우회.
3. **Parsing**: Jsoup의 `Document` 객체를 활용해 HTML 구조 파악.
   - 네이버의 `blind` 클래스를 활용해 쪼개진 텍스트를 한 번에 추출하는 팁 습득.
   - 클래스가 없는 경우 `nth-child`나 `get(index)`를 활용한 순서 기반 추출 방식 이해.

### 트러블슈팅
- **문제**: `searchList.naver` 검색 URL 사용 시 HTTP 500 에러 발생.
- **해결**: 상세 페이지 URL(`item/main.naver?code=`)로 타겟을 변경하고 헤더 정보를 보강하여 해결.
