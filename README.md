# GitHub 연동방법
(1) 프로젝트 디렉토리 내 저장소 생성
```
git init
```
(2) 원격 저장소와 연결
```
git remote add 'remote name' 'url'
```

# 소스 형상관리 방법
(1) 변경사항 전부 스테이징  
(2) 스테이징 내용 확인 후 커밋 (커밋 메시지 자세하게 작성)  
(3) 현재 브런치 원격 저장소에 Push
```
git push 'remote name' 'branch name'
```

# Git 브런치
- 브런치 생성
```
git checkout -b 'create branch name' 'parent branch name'
```
- 브런치 삭제
```
git branch -d 'branch name'
```
- 브런치 이동
```
git checkout 'branch name'
```
- 브런치 병합
```
git merge --no-ff 'branch name'
```