### 1단계 요구 사항

- InputStream을 한 줄 단위로 읽기 위해 BufferedReader를 생성한다.
  - 구글에서 "java inputstream bufferedreader"로 검색 후 문제 해결
- BufferedReader.readLine() 메소드를 활용해 라인별로 HTTP 요청 정보를 읽는다.
- HTTP 요청 정보 전체를 출력한다.
  - 헤더 마지막은 `while().(!"".equals(line))`로 확인 가능한다.
  - line에 null 값인 경우에 대한 예외 처리도 해야 한다. 그렇지 않을 경우 무한 루프에 빠진다. `if (line == null){return;}`

### 2단계 요구 사항
- HTTP 요청 정보의 첫 라인에서 요청 URL을 추출한다.
  - `line.split(" ")`을 활용
- 구현은 별도의 유틸 클래스를 만들고 단위 테스트를 만들어서 진행하면 편하다.
