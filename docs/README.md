# 크리스마스 프로모션 프로젝트
## 핵심 기능
**방문 예정 날짜에 따른 적용대상 이벤트 혜택들을 계산한다.**

## 기능 목록
**목표**
  - [ ] 중복된 할인과 증정을 허용해서, 고객들이 혜택을 많이 받는다는 것을 체감할 수 있게 한다.
  - [ ] 올해 12월에 지난 5년 중 최고의 판매 금액을 달성한다.
  - [ ] 12월 이벤트 참여 고객의 5% (이상)이 내년 1월 새해 이벤트에 재참여하기

## 시퀀스 다이어그램
- [x] 메뉴 정보를 초기화한다.
  메뉴 정보
  ```text
  <애피타이저>
  양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
  
  <메인>
  티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
  
  <디저트>
  초코케이크(15,000), 아이스크림(5,000)
  
  <음료>
  제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
  ```
- [x] 안내 멘트를 출력한다.
    ```text
  안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
  ```
- [x] 고객들에게 예상 방문 날짜를 입력받는다.
  ```text
  12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
  ```
- [x] 방문 날짜 입력에 대한 예외를 검증하고 실패 시 재입력시킨다.
  - [x] 공백이 아닌지 검증한다. 
  - [x] 숫자인지 검증한다.
  - [x] 메모리 초과 위험이 있는 수치인지 검증한다.
  - [x] 방문할 날짜는 1~31 숫자로만 입력받는다.
    - [x] 1 이상 31 이하의 숫자가 아닌 경우 예외 메시지
    ```text
    [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
    ```
  - [x] 모든 에러 메시지는 "[ERROR]"로 시작하도록 작성한다.
- [x] 예상 방문 날짜 데이터를 저장한다.
- [x] 주문 정보를 정해진 형식으로 입력받는다.
  ``` text
  주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  ```
- [x] 주문 입력에 대한 예외를 검증하고 검증 실패 시 재입력시킨다.
  - [x] ","(쉼표)를 기준으로 문자열을 구분했을 때 공백이 아닌지 검증한다.
  - [x] "-"(바)를 기준으로 문자열을 구분했을 때 오른쪽이 숫자인지 검증한다.
    - [x] 정수인지 검증한다.
    - [x] 메모리 초과 위험이 있는 수치인지 검증한다.
    - [x] 입력 받은 값이 있는지 검증한다.
    - [x] 자연수인지 검증한다. (0도 허용하지 않는다.)
  - [x] "-"(바)를 기준으로 문자열을 구분했을 때 왼쪽이 메뉴 목록에 해당되는 메뉴인지 검증한다.
    - [x] 중복 메뉴를 입력했는지 검증한다.
    - [x] 입력 받은 값이 있는지 검증한다.
    - [x] 음료만 주문한 것은 아닌지 검증한다.
      ```text
      [ERROR] 음료만 주문할 수 없습니다. 다시 입력해주세요.
      ```
    - [x] 메뉴가 한 번에 20개를 초과해서 주문됐는지 확인한다.
      ```text
      [ERROR] 메뉴는 최대 20개까지 주문할 수 있습니다. 다시 입력해주세요.
      ```
  - [x] 메뉴 입력시 예외 메시지 - 음료만 주문했을 때와 20개 초과 주문을 제외하고는 모두 같은 메시지다.
    ```text
    [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
    ```
- [x] **방문 예정 날짜에 따른 이벤트 혜택들을 계산한다.**
  - [x] 이벤트 적용 대상인지 확인한다.
    - [x] 총주문 금액이 10,000원 이상이면 적용 대상이다.
  - [x] 크리스마스 디데이 할인 적용 날짜인지 계산한다. - ChristmasDayCalculator$supports
    - [x] 적용 날짜는 12.1, 12.2... 12.30까지로 매주 금토다.
    - [x] 적용 날짜라면, {(${일수} - 1) * 100 + 1000}만큼 할인한다.
  - [x] 평일 할인 적용 날짜인지 계산한다.
    - [x] 적용 날짜는 일요일~목요일이고, 디저트 메뉴를 주문했을 때만 적용된다.
    - [x] 적용 날짜라면, **디저트 메뉴**를 **1개당** 2,023원 할인한다.
  - [x] 주말 할인 적용 날짜인지 계산한다.
    - [x] 적용 날짜는 금요일, 토요일이고, 메인 메뉴를 주문했을 때만 적용된다.
    - [x] 적용 날짜라면, **메인 메뉴**를 **1개당** 2,023원 할인한다.
  - [x] 특별 할인 적용 날짜인지 계산한다.
    - [x] 적용 날짜는 12.3, 12.10... 매주 일요일과 12.25이다.
    - [x] 적용 날짜라면, 총 주문 금액에서 1,000원을 할인한다.
  - [x] 증정 이벤트 대상인지 확인한다.
    - [x] 할인 전 총주문금액이 12만 원 이상이면 대상이다. 
    - [x] 적용 대상이라면 샴페인 1개를 증정한다.
    - [x] 우테코 식당의 목표 1번에 의거하여, 24만 원 등 총주문 금액이 많으면 그에 해당하는 샴페인을 추가로 증정한다.
- [x] 이벤트 별로 계산한 혜택들을 총합한다. - EventCalculatorsManager
  - [x] 각 이벤트별로 혜택받은 금액이 얼마씩인지 관리한다.
- [x] 총혜택 금액을 계산한다. - EventCalculatorsManager
  - [x] 할인 금액의 합계 + 증정 메뉴의 가격
- [x] 할인 후 예상 결제 금액을 계산한다.
  - [x] 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
- [x] **총혜택 금액에 따라** 12월 이벤트 배지를 부여한다.
  - 5천 원 이상: 별 
  - 1만 원 이상: 트리
  - 2만 원 이상: 산타
- [x] 방문 예정 날짜와 함께 받을 이벤트 혜택을 미리 보여준다는 안내 멘트를 날린다.
  ```text
  ${방문 날짜/12월 26일}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
  ```
- [x] 주문 메뉴를 출력한다.(순서는 자유롭게 한다.)
  예시다.
  ```text
  <주문 메뉴>
  ${메뉴} ${개수}개
  타파스 1개
  제로콜라 1개
  ```
- [x] 할인 전 총 주문 금액을 출력한다.
  - [x] 특히 금액은 ","로 형식을 맞춘다.
  ```text
  <할인 전 총주문 금액>
  ${총주문 금액}원
  ```
- [x] 증정 메뉴를 출력한다.
  - [x] 샴페인이거나 없으면 없음으로 출력한다.
  ```text
  <증정 메뉴>
  없음
  ```
  ```text
  <증정 메뉴>
  샴페인 1개
  ```
- [x] 혜택 내역을 출력한다.
  - [x] 특히 금액은 ","로 형식을 맞춘다.
  - [x] 만약 없으면 "없음"으로 출력한다.
  - [x] 혜택 받은 이벤트 중에서 없으면 그냥 출력을 하지 않는다.
  - [x] 출력 순서는 자유롭게 출력한다.
  ```text
  <혜택 내역>
  크리스마스 디데이 할인: -1,200원
  평일 할인: -4,046원
  특별 할인: -1,000원
  증정 이벤트: -25,000원
  ```
  ```text
  <혜택 내역>
  없음
  ```
- [x] 총혜택 금액을 출력한다.
  - [x] 특히 금액은 ","로 형식을 맞춘다.
  - [x] "0원"으로 출력한다.
  ```text
  <총혜택 금액>
  -31,246원
  ```
  ```text
  <총혜택 금액>
  0원
  ```
- [x] 할인 후 예상 결제 금액을 출력한다.
  - [x] 할인 받은 게 없으면 할인 받기 전의 결제 금액과 같은 금액으로 출력한다.
  ```text
  <할인 후 예상 결제 금액>
  8,500원
  ```
- [x] 이벤트 배지를 출력한다.
    - [x] 부여된 배지가 없으면 "없음"으로 출력한다.
  ```text
  <12월 이벤트 배지>
  산타
  ```
  ```text
  <12월 이벤트 배지>
  없음
  ```

## 도메인 모델 - 핵심 도메인 순서대로
### EventCalculatorAdapter
1. 역할 : 방문 날짜에 해당되는 이벤트들을 적용시켜주는 역할 
2. 상태
3. 행위 
   - public boolean supports(Day day, Money priceBeforeDiscount, List menus)
   - public Money discountPrice(OrderForEvents orderForEvents)
4. 구현 객체 
   - ChristmasDayCalculator 
   - WeekdayCalculator 
   - WeekendCalculator 
   - SpecialCalculator 
   - PresentationCalculator

### Event
1. 역할 : 이벤트 종류별로 날짜 정보를 갖는 열거형
2. 상태 : CHRISTMAS_D_DAY_EVENT, WEEKDAY_EVENT, WEEKEND_EVENT, SPECIAL_EVENT, PRESENTATION_EVENT 
   - 각 상태가 갖는 멤버변수 : String name
3. 행위
   - String getName()
   - EventCalculatorAdapter getEventCalculator()

### BenefitCalculator
1. 역할 : 혜택 결과를 총괄하는 역할
2. 상태 : OrderCalculator
3. 행위 
   - public CalculateResult generateBenefitDetails(Day visitingDay, OrderMenus orderMenus)

### MenuKind
1. 역할 : 메뉴 유형 정보를 갖는 열거형
2. 상태 : APPETIZER, MAIN, DESSERT, BEVERAGE
3. 행위
   - boolean isKindOf(Menu menu)

### Menu
1. 역할 : 메뉴 정보를 갖는 열거형
2. 상태 : BUTTON_MUSHROOM_SOUP, TAPAS, CAESAR_SALAD, T_BONE_STEAK, BARBCUE_RIBS, SEAFOOD_PASTA, CHRISTMAS_PASTA, CHOCOLATE_CAKE, ICE_CREAM, COKE_ZERO, RED_WINE, CHAMPAGNE
   - 각 상태가 갖는 변수 : Money price, String name
3. 행위
   - Money getPrice()
   - String geName()

### BadgeGiver
1. 역할 : 이벤트 배지를 부여하는 역할
2. 상태
3. 행위
   - public Badge getRightBadge(Money totalBenefitAmount)

### Day
1. 역할 : 날짜의 값에 대해서 검증하고 해당 요일을 알려주는 역할
2. 상태 : Int date
3. 행위
   - private void validate(int date)
   - boolean isFriday()
   - boolean isSaturday()
   - boolean isWeekday()
   - boolean isWeekend()
   - int daysFromFirstDate()

### OrderCalculator
1. 역할 : 주문 목록들을 받으면 할인 혜택을 받기 전의 총주문 금액을 계산한다.
2. 상태
3. 행위
   - Money calculateTotalPrice()
   - OrderBeforeEvents calculateTotalPriceAndCanApplyToEvents(List<> menues)
   - private int generateTotalAmount(List<> menues)
   - private boolean canApplyToEvents(int totalAmount)

### EventCalculatorsManager
1. 역할 : 이벤트 계산기별로만 계산 결과를 통합해준다.
2. 상태 : EnumMap<Event, Money> benefitAmounts
3. 행위
   - EventsResult generateEventsResult(visitingDay, orderMenus, totalPriceBeforeDiscount)
   - private void generateBenefitAmounts(visitingDay, orderMenus, totalPriceBeforeDiscount)
   - private EnumMap<Menu, Integer> generatePresentCount()
   - private Money calculateTotalBenefitAmount

### Badge
1. 역할 : 배지 정보를 갖는 열거형
2. 상태 : STAR, TREE, SANTA
3. 행위 : boolean deserveThisBadge(Money totalBenefitPrice)