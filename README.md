# 1.주제
콘서트에 대한 정보를 제공하고 콘서트 예약까지 완료할 수 있는 웹페이지를 제작한다.

# 2.엔티티 관계도

<img width="805" alt="엔티티관계도" src="https://user-images.githubusercontent.com/96512568/178138832-942084c4-bee0-4a09-adde-a8a078339b31.png">
 
1. Concert - Singer
특정 Concert에 참가하는 Singer는 0명, 1명, 여러명일 수 있다. 또한 특정 Singer가 참가하는 Concert는 0개, 1개, 여러개일 수 있다. 즉, Concert와 Singer는 **다대다(N:M) 관계**고 **Optional**이다(특정 Concert에 참가하는 Singer가 한명도 없을 수 있고, 특정 Singer가 참여하는 Concert가 한개도 없을 수 있다는 뜻). 그래서 위와 같이 Concert와 Singer테이블 사이에 Concert_Singer테이블을 추가로 생성해 **1:N 관계**로 다대다 관계를 표현했다. 특정 Concert_Singer은 무조건 Concert와 Singer에 대한 내용을 가져야 하므로 Concert와 Singer가 **Mandatory**하고, Concert_Singer에는 특정Concert나 특정Singer에 대한 정보가 아예 없거나 1개, 여러개일 수 있기 때문에 **Optional**하다.         



2. Concert - Order
특정 Concert에 예약한 Order는 0개, 1개, 여러개일 수 있다. 그러므로 **1:N 관계**이며 Concert는 **Mandatory**, Order는 **Optional**한 관계(특정 Order에는 예약된 Concert가 없으면 안되고, 특정 Concert에는 예약된 Order가 없을 수 도 있다는 뜻)를 표현했다.            


# 3.웹 구성
기본적으로 w3schools에서 제공하는 무료 템플릿을 이용했다. 'concert/src/main/webapp/tryw3css_templates_band.htm'이 최종적인 웹사이트다.               
[참조](https://www.w3schools.com/w3css/w3css_templates.asp)https://www.w3schools.com/w3css/w3css_templates.asp               



## 3.1 비동기
### 3.1.1 htm상 자바스크립트 코드, 서블릿상 자바 코드
비동기 기술을 이용해 콘서트에 참여하는 가수들의 정보를 웹페이지 중간에 계속해서 불러오게 설정했다. 자바스크립트로 처리한 내용은 다음과 같다.

```javascript
var count = 0;
var jsonConcert = null;
var concertCount = null;
var jsonSinger = null;
function loadAllSinger() {
	console.log("카운트 : "+count);
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
		if (xhttp.status == 200) {
			if (count == 0) {
				console.log("0번");
				let res_data = this.responseText;
				console.log(res_data);
				jsonConcert = JSON.parse(res_data);
				concertCount = Object.keys(jsonConcert).length;
				let imageBox = document
						.getElementById("showAllSinger");
				imageBox.innerHTML = "<h2>콘서트 라인업이 궁금하신가요?</h2><br>";
			} else if (count == 1) {
				console.log("1번");
				let res_data = this.responseText;
				jsonSinger = JSON.parse(res_data);
				let imageBox = document
						.getElementById("showAllSinger");
				imageBox.innerHTML = "<h2>전체가수</h2><br>";
				for (i in jsonSinger) {
					console.log("확인용@");
					let singerName = jsonSinger[i];
					imageBox.innerHTML += `<div class="w3-third"><p>${singerName}</p><img src="images/가수${i}_${singerName}.jpg" class="w3-round w3-margin-bottom" alt="Random Name" style="width:30%"></div>`;
				}
			} else if (count <= concertCount + 1) {
				console.log("0,1번아님");
				let res_data = this.responseText;
				let imageBox = document.getElementById("showAllSinger");
				let num = count-1;
				imageBox.innerHTML = `<h2>콘서트${num} 가수</h2><br>`;
				console.log(res_data);
				for (i in res_data){
					console.log("확인용%");
					let singerId = res_data[i];
					console.log(singerId);
					let singerName = jsonSinger[singerId];
					imageBox.innerHTML += `<div class="w3-third"><p>${singerName}</p><img src="images/가수${singerId}_${singerName}.jpg" class="w3-round w3-margin-bottom" alt="Random Name" style="width:30%"></div>`;
				}
				
			}

			count += 1;
			if (count >= concertCount + 2) {
				count = 0;
			}
		}else{
			console.log("통신대기중..");
		}
	}

	///////////////////////////////////////////////////////////////
	if (count == 0) {
		xhttp.open("get", "concert?command=getAllConcert", true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	} else if (count == 1) {
		xhttp.open("get", "concert?command=getAllSingers", true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	} else if (count <= concertCount + 1) {
		xhttp.open("get", `concert?command=getSingersByConcert&data=${count}`,
				true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	}

}
let interval = setInterval(loadAllSinger, 3000);
```

큰 틀로 설명하면 **웹페이지 중간부분에 콘서트에 참여하는 가수에 대한 정보를 불러오는 loadAllSinger()함수**를 만들고, 이를 **setInterval함수로 설정 시간마다 연속적으로 호출**하는 방식이다. 위 자바스크립트를 뜯어보면서 동작방식을 알아본다.                 

- loadAllSinger()함수 생성 이전에 전역변수처럼 사용할 변수 선언
	- var count = 0; : count가 0부터 concertCount(밑에 설명) + 1 까지 계속해서 돌게 설정하여 각 숫자별로 다른 로직을 실행한다.( ex) count가 0일 때는 "콘서트 라인업이 궁금하신가요?"화면에 출력 및 모든 콘서트정보를 jsonConcert(밑에 설명)에 저장, count가 1일 때는 "전체가수"화면에 출력 및 모든 가수정보를 jsonSinger(밑에 설명)에 저장 이후 모든 가수 이미지와 이름 출력 ... )
	- var jsonConcert = null; : 위에서 언급된 count가 0일 때 콘서트정보를 json형식으로 저장할 때 쓰일 변수
	- var concertCount = null; : 위에서 언급된 count가 0일 때 콘서트 개수를 json형식으로 저장할 때 쓰일 변수
	- var jsonSinger = null; : 위에서 언급된 count가 1일 때 가수정보를 json형식으로 저장할 때 쓰일 변수

위 변수를 통해 전체적인 흐름을 유추할 수 있다.             
count 0(총 콘서트 개수와 종류 불러오기) **==>** count 1(총 가수 정보 불러오기) **==>** count 2,3,4 ... 총 콘서트 개수만큼 count 진행(해당 콘서트에 참여하는 가수 정보 화면에 출력)        

- loadAllSinger()함수
기본적으로 **XMLHttpRequest**를 이용했다. XMLHttpRequest는 서버에서 간단하게 원하는 데이터만 호출할 수 있는 방법이다.

```javascript
function loadAllSinger() {
	console.log("카운트 : "+count);
	const xhttp = new XMLHttpRequest();

	// (중략)

	if (count == 0) {
		xhttp.open("get", "concert?command=getAllConcert", true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	} else if (count == 1) {
		xhttp.open("get", "concert?command=getAllSingers", true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	} else if (count <= concertCount + 1) {
		xhttp.open("get", `concert?command=getSingersByConcert&data=${count}`,
				true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		xhttp.send();
	}
}
```

가운데 xhttp.onload = function() 를 중략한 이유는 이 코드는 XMLHttpRequest객체가 서버와의 통신이 마치고 돌아온 경우 실행되는 코드이기 때문이다.         

위 내용을 보면 loadAllSinger함수는 실행되면 카운트정보를 콘솔에 출력한 뒤, XMLHttpRequest객체 xhttp를 생성한다. 그리고 count가 0일 때, 1일 때, 0도 1도 아니며 concertCount + 1 보다 작거나 같을 때로 나뉘어 다른 명령을 서버로 전송한다. 이때 xhttp.open함수로 각 경우에 따라 command에 대한 값으로 다른 값을 넣어 서버로 보내는 모습을 확인할 수 있다. count가 0일 때는 'getAllconcert', count가 1일 때는 'getAllSingers', 나머지 경우에는 'getSingersByConcert' 그리고 이 경우에는 data에 대한 값으로 count값도 넣어 보냄을 확인할 수 있다. 서버에서는 해당 command값에 맞춰 작업을 해주는데 간단하게 'getAllConcert'를 command값으로 받았을 때, 'getSingersByConcert'를 command값으로 받았을 때 서버에서 작동하는 코드를 확인해본다.


```java
// 'getAllConcert'를 command값으로 받았을 때
private void getAllConcert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url = "showError.jsp";
	try {
		request.setAttribute("getAllConcert", service.getAllConcert());
		ArrayList<ConcertDTO> getAllConcert = service.getAllConcert();
		url = "showSuccess.jsp";
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw= response.getWriter();
		JSONObject result = new JSONObject();
		System.out.println(getAllConcert);
		for(ConcertDTO concert:getAllConcert) {
			result.put(Integer.toString(concert.getConcertId()), concert.getConcertName());
		}
		System.out.println(result);
		System.out.println(result.getClass());
		pw.print(result);
	} catch (Exception e) {
		request.setAttribute("errMsg", e.getMessage());
		e.printStackTrace();
	}
}



// 'getSingersByConcert'를 command값으로 받았을 때
private void getSingersByConcert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("확인용메시지123");
	int concertId = Integer.parseInt(request.getParameter("data"))-1;
	response.setContentType("text/html;charset=UTF-8");
	ArrayList<Integer> result = new ArrayList<>();;
	StringBuilder result2 = new StringBuilder();
	String result3 = null;
	try {
		result = service.getSingersByConcert(concertId);
		System.out.println(result);		
		} catch (SQLException | NotExistException e) {
		e.printStackTrace();
	}
	for (Integer i: result) {
		result2.append(i);
	}
	result3 = result2.toString();
	PrintWriter pw= response.getWriter();
	pw.print(result3);
}
```

위 코드만으로는 정확한 동작방식을 이해하기 어렵다. 간단하게 service객체에서 특정 메서드를 이용하면 DB에서 원하는 정보를 가져온다고 생각하면 이해가 쉽다. 두 경우 모두 원하는 값을 불러오고 나서는 PrintWriter 객체에 해당 정보를 넣은 뒤 다시 자바스크립트로 신호를 보낸다. 그럼 위에서 중략했었던 xhttp.onload에서 해당 신호를 받아 익명함수를 실행하게 된다.            

```javascript
xhttp.onload = function() {
		if (xhttp.status == 200) {
			if (count == 0) {
				console.log("0번");
				let res_data = this.responseText;
				console.log(res_data);
				jsonConcert = JSON.parse(res_data);
				concertCount = Object.keys(jsonConcert).length;
				let imageBox = document
						.getElementById("showAllSinger");
				imageBox.innerHTML = "<h2>콘서트 라인업이 궁금하신가요?</h2><br>";
			} else if (count == 1) {
				console.log("1번");
				let res_data = this.responseText;
				jsonSinger = JSON.parse(res_data);
				let imageBox = document
						.getElementById("showAllSinger");
				imageBox.innerHTML = "<h2>전체가수</h2><br>";
				for (i in jsonSinger) {
					console.log("확인용@");
					let singerName = jsonSinger[i];
					imageBox.innerHTML += `<div class="w3-third"><p>${singerName}</p><img src="images/가수${i}_${singerName}.jpg" class="w3-round w3-margin-bottom" alt="Random Name" style="width:30%"></div>`;
				}
			} else if (count <= concertCount + 1) {
				console.log("0,1번아님");
				let res_data = this.responseText;
				let imageBox = document.getElementById("showAllSinger");
				let num = count-1;
				imageBox.innerHTML = `<h2>콘서트${num} 가수</h2><br>`;
				console.log(res_data);
				for (i in res_data){
					console.log("확인용%");
					let singerId = res_data[i];
					console.log(singerId);
					let singerName = jsonSinger[singerId];
					imageBox.innerHTML += `<div class="w3-third"><p>${singerName}</p><img src="images/가수${singerId}_${singerName}.jpg" class="w3-round w3-margin-bottom" alt="Random Name" style="width:30%"></div>`;
				}
				
			}

			count += 1;
			if (count >= concertCount + 2) {
				count = 0;
			}
		}else{
			console.log("통신대기중..");
		}
	}
```

if (xhttp.status == 200) 는 통신상태가 양호한지 체크하는 부분이다. 이후에는 위에서 했던 작업과 비슷하게 count가 0일 때, 1일 때, 나머지 경우로 나누어 따로 작업을 해준다.         
- count 0 : 서버에서 받아온 총 콘서트 정보를 JSON형식으로 파싱해 해당 jsonConcert에 해당 값 대입, 총 콘서트 개수를 concertCount에 저장
- count 1 : 서버에서 받아온 총 가수 정보를 JSON형식으로 파싱해 해당 jsonSinger에 해당 값 대입,
- count 0도 1도 아니고 concertCount+1보다 작거나 같을 경우 : 위 단계에서 만들어진 jsonConcert와 jsonSinger를 이용해 해당 콘서트에 참여하는 가수들의 이름과 이미지를 화면에 출력




### 3.1.2 출력화면  

<img width="1277" alt="1" src="https://user-images.githubusercontent.com/96512568/178143857-320ed97b-2309-4a01-9792-cbc8ea0141dc.png">           
<img width="1276" alt="2" src="https://user-images.githubusercontent.com/96512568/178143870-8b38d88c-3418-4bec-aed4-0d2c5d2f0f67.png">           
<img width="1279" alt="3" src="https://user-images.githubusercontent.com/96512568/178143878-e702e7be-372c-4117-ad72-b372d6eb4dd3.png">           

(데이터베이스에 레코드를 넣는 부분에서 잘못해서 중복된 내용을 넣어 위와 같이 콘서트1에 마이클 잭슨이 두 번 들어가는 실수가 발생했습니다..)


# 4.후기
이번 프로젝트는 시간부족으로 많은 기능을 넣지 못했다. 초반에 생각했던 예약기능도 넣지 못했다. 다음에 웹프로젝트가 한번 더 있는데, 그때는 더 알차게 프로젝트를 진행해야 겠다.
