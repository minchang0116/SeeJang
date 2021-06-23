# See 장
<img src="/frontend/cart/src/assets/cart.gif" width="40%" height="30%" title="see장 로고" alt="see장로고"></img>

## Prize
SSAFY 공통프로젝트 1등(2020.02)

## 시연영상
[SEE 장](https://www.youtube.com/watch?v=snr8gCf6fbk)

## 프로젝트 개요
### 🛠개발기간 
2021.01~2021.02.19

### 🎞기획의도
>장을 볼 때 불편했던 경험 있으신가요?<br>사람들은 종종 오프라인에서 장을 볼 때 온라인에서 사는게 더 쌀지, 지금 사는게 더 쌀지 고민하며 핸드폰으로 쿠팡이나 네이버 앱을 켜서 상품명을 검색하고 가격을 비교해보곤 합니다.<br>혹시 이런 불편을 해소할 수 있는 장보기 앱이 시중에 나와있는지 조사해보았지만 대부분 장보기 목록을 작성하는 것이 끝이었습니다.<br><br>**그래서 저희는 오프라인에서 장을 볼 때 좀 더 편하고, 좀 더 스마트하게 장을 볼 수 있도록 도와주는 앱을 개발하기로 했습니다.**<br><br>앱 기획을 하던 중 앞이 잘 보이지 않거나 아예 보이지 않는 시각 장애인들의 장보기는 여전히 다른 사람의 도움 없이는 보기 힘든 환경이라는 것을 알게 되었습니다. <br><br>기술이 모두를 고려하지 않으면 차별의 도구가 될 수도 있지 않을까요?<br>그래서 결정했습니다.<br>비장애인 뿐만 아니라 시각 장애인의 장보기도 도와주는 앱을 만들기로.<br><br>그래서 저희 앱의 이름인 see 장은 <br><br>**장을 본다는 의미와 앞이 잘 보이지 않는 시각 장애인들도 앞을 보는 것처럼 장을 볼 수 있다는 의미가 담겨 있습니다.**


## 🍤주요기능

#### [시각장애인]

### 상품 위치 찾기
+ STT기술을 통해 음성인식의 결과를 백엔드로 전달하고, 백엔드에서 넘어온 결과를 TTS를 통해 휴대폰 기준 8방향을 시각장애인 사용자에게 전달
  <br>
  <img src="https://user-images.githubusercontent.com/57394523/115141252-2dce1e00-a076-11eb-924d-ffccc0c7b641.png"  height="500" width="300">

### 상품 확인하기
+ 현재 사진을 찍은 상품이 어떤 상품인지 TTS를 통해 시각장애인 사용자에게 전달
  <br>
  <img src="https://user-images.githubusercontent.com/57394523/115141325-8c939780-a076-11eb-915c-040ba6ea698e.png"  height="500" width="300">


#### [비장애인]
* 이번주 인기상품

### 쇼핑리스트
+ 장보기 전 쇼핑리스트를 작성하고 대략적 가격을 알 수 있는 기능
  <br>
  <img src="https://user-images.githubusercontent.com/57394523/115141007-25c1ae80-a075-11eb-8f84-9071a4ad2109.png"  height="500" width="300">

### 상품찾기
+ 카메라로 상품 사진을 찍으면 인식해 최저가 링크 + 앱 내 리뷰를 띄어주는 기능
<br>
<div style="display:inline-block;">
  <img src="https://user-images.githubusercontent.com/57394523/115140778-f8c0cc00-a073-11eb-9c2d-e014497d033d.png"  height="500" width="300">
  <img src="https://user-images.githubusercontent.com/57394523/115141091-87821880-a075-11eb-8dec-7ab1fa9292c1.png"  height="500" width="300">
  <img src="https://user-images.githubusercontent.com/57394523/115141156-d7f97600-a075-11eb-9d42-7197e4b92a60.png"  height="500" width="300">
</div>

### SSG몰 리뷰확인

+ SSG몰의 링크를 통해서 리뷰 확인 가능
  <br>
  <img src="https://user-images.githubusercontent.com/57394523/115141130-af717c00-a075-11eb-949c-30f35033881d.png"  height="500" width="300">


### 최저가 찜목록

+ 찜목록을 통해 페이지로 이동 가능 
  <br>
  <div style="display:inline-block;">
    <img src="https://user-images.githubusercontent.com/57394523/115141194-fa8b8f00-a075-11eb-82ff-7a732d62ddd1.png"  height="500" width="300">
    <img src="https://user-images.githubusercontent.com/57394523/115141223-0ecf8c00-a076-11eb-8061-3e289cf4e5bf.png"  height="500" width="300">
  </div>

<br>

## Our Team

* 팀장 강민창([@minchang0116](https://github.com/minchang0116 "github link"))
* 팀원 강유정([@yujeong0](https://github.com/yujeong0 "github link"))
* 팀원 곽충섭([@NICEGINI](https://github.com/NICEGINI "github link"))
* 팀원 김유리([@Yu-ri-62](https://github.com/Yu-ri-62 "github link"))
* 팀원 정준영([@dkwjdi](https://github.com/dkwjdi "github link"))
* 팀원 지서연([@seoyounji](https://github.com/seoyounji "github link")) <br><br>

<br>

# ⚙Tech Stack
* vue.js
* spring boot
* MySQL
* redis
* Docker
* tensorflow

<br>

# 개발 환경 세팅
<h3>롬복 적용<h3>
<a>yunyoung1819.tistory.com/95</a>

 ```cmd
cd .\front
npm install
npm build
 ```
 
<h3>Redis 도커 실행<h3>
  
```cmd
docker run -p 6379:6379 --name redis_boot -d redis
docker exec -i -t redis_boot redis-cli
 ```
