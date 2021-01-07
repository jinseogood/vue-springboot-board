# 게시판 APP View 만들기

## 개발 환경
- @vue/cli 4.5.9 version
- axios 0.21.1 version
- vue-router 3.2.0 version
- vuetify 2.2.11 version
- vuex 3.4.0 version
- @toast-ui/vue-editor 2.5.1 version

## 프로젝트 생성
- vue create 'Project Name'

## NPM 설치
- npm install | i

## 프로젝트 실행
- npm run serve

## 필요 컴포넌트
- 상단 메뉴
- 날씨
- 시간
- 게시판 목록
- 게시판 작성, 수정
- 에러
- 조회수
- 댓글

## 오류
- npm run serve 오류('vue-cli-service'은(는) 내부 또는 외부 명령, 실행할 수 있는 프로그램, 또는 배치 파일이 아닙니다)  
  (1) NPM 캐시 제거 : npm cache clean --force  
  (2) 프로젝트 내 node_modules, package-lock.json 파일 제거  
  (3) NPM 설치 : npm i 또는 npm install  

## App Title 변경
- vue.config.js 에 속성 추가
```
"chainWebpack": (config) => {
  config
    .plugin('html')
    .tap((args) => {
      args[0].title = 'Title';
      return args;
    });
},
```

## 파일 절대경로 설정
- Project 폴더 내 jsconfig.json 생성 및 속성 추가
```
{
  "compilerOptions": {
      "baseUrl": ".",
      "paths": {
          "~/*": [
              "./*"
          ],
          "@/*": [
              "./src/*"
          ],
      }
  },
  // compilerOptions 에 제외될 폴더
  "exclude": [
      "node_modules",
      "dist"
  ]
}
```

## views 와 components 차이
- Router 에서 보여주는 컴포넌트 파일은 views 폴더에 위치, 그 외에는 components 폴더에 위치한다.

## 사용자 정의 플러그인 사용법
(1) @/plugins 에 플러그인.js 파일 생성  
(2) 예제 코드
```
const myPlugin = {}

myPlugin.install = function(Vue) {
  // 1. 전역 메소드 또는 속성 추가
  Vue.myGlobalMethod = function () {
    // 필요한 로직 ...
  }

  // 2. 전역 에셋 추가
  Vue.directive('my-directive', {
    bind (el, binding, vnode, oldVnode) {
      // 필요한 로직 ...
    }
    ...
  })

  // 3. 컴포넌트 옵션 주입
  Vue.mixin({
    created: function () {
      // 필요한 로직 ...
    }
    ...
  })

  // 4. 인스턴스 메소드 추가
  Vue.prototype.$myMethod = function (methodOptions) {
    // 필요한 로직 ...
  }

  // 예제 메소드(페이지 이동)
  Vue.prototype.movePage = function(url) {
    if (this.$route.path !== url) {
      this.$router.push(url)
    }
  }
  // 예제 메소드(이전페이지 이동)
  Vue.prototype.prevPage = function() {
    window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
  }
}

export default myPlugin
```
(3) main.js 에 플러그인.js import 및 Vue.use(플러그인) 처리  
(4) 사용할 곳에서 this.메소드명 으로 접근

## 상위 컴포넌트에서 하위 컴포넌트 접근 방법
- 컴포넌트 태그에 ref 속성 추가 후 필요한 곳에서 this.$refs.속성명 으로 접근가능
- 하위 컴포넌트 내 선언된 컴포넌트의 하위 컴포넌트를 접근하려면 this.$refs.속성명.$refs.속성명2 로 접근가능

## no-unused-vars 해결 방법
- vue.config.js 파일 내 설정으로 ESLint 에러 표시 레벨을 명령어 입력 창 레벨로 내려준다.
```
module.exports = {
  devServer: {
    overlay: false,
  },
}
```

## ESLint 표시 설정
- no-unused-vars 표시 설정을 변경하려면 .eslintrc.js 파일 내 설정
```
rules: {
  "no-unused-vars": 1 // 0 (경고/알림 제거), 1 (경고), 2 (빨간줄, 에러)
```
- ESLint 검사를 건너 뛸 곳에 주석 달기
```
// eslint-disable-next-line 다음줄 건너뛰기
/* eslint-disable */ 전체 파일 건너뛰기
/* eslint-disable */ ~ /* eslint-enable */ 해당 범위 건너뛰기
```