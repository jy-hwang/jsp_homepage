<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">

    <link href="/assets/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
        #visual .content-container {
            height: inherit;
            display: flex;
            align-items: center;

            background: url("/assets/images/mypage/visual.png") no-repeat center;
        }
    </style>
</head>
<body>
  <!-- header 부분 -->
  




    <header id="header">
        
        <div class="content-container">
            <!-- ---------------------------<header>--------------------------------------- -->

            <h1 id="logo">
                <a href="/index.html">
                    <img src="/assets/images/logo.png" alt="뉴렉처 온라인" />

                </a>
            </h1>

            <section>
                <h1 class="hidden">헤더</h1>

                <nav id="main-menu">
                    <h1>메인메뉴</h1>
                    <ul>
                        <li><a href="/guide">학습가이드</a></li>
                        <li><a href="/course">강좌선택</a></li>
                        <li><a href="/answeris/index">AnswerIs</a></li>
                    </ul>
                </nav>

                <div class="sub-menu">

                    <section id="search-form">
                        <h1>강좌검색 폼</h1>
                        <form action="/course">
                            <fieldset>
                                <legend>과정검색필드</legend>
                                <label>과정검색</label>
                                <input type="text" name="q" value="" />
                                <input type="submit" value="검색" />
                            </fieldset>
                        </form>
                    </section>

                    <nav id="acount-menu">
                        <h1 class="hidden">회원메뉴</h1>
                        <ul>
                            <li><a href="/index.html">HOME</a></li>
                            <li><a href="/member/login.html">로그인</a></li>
                            <li><a href="/member/agree.html">회원가입</a></li>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">고객메뉴</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/assets/images/txt-mypage.png" alt="마이페이지" /></a></li>
                            <li><a href="/notice/list.html"><img src="/assets/images/txt-customer.png" alt="고객센터" /></a></li>
                        </ul>
                    </nav>

                </div>
            </section>

        </div>
        
    </header>

  <!-- --------------------------- <visual> --------------------------------------- -->
  <!-- visual 부분 -->
  
  <div id="visual">
    <div class="content-container"></div>
  </div>
  <!-- --------------------------- <body> --------------------------------------- -->
  <div id="body">
    <div class="content-container clearfix">

      <!-- --------------------------- aside --------------------------------------- -->
      <!-- aside 부분 -->
      


      <aside class="aside">
        <h1>ADMIN PAGE</h1>

        <nav class="menu text-menu first margin-top">
          <h1>마이페이지</h1>
          <ul>
            <li><a href="/admin/index.html">관리자홈</a></li>           
            <li><a href="/teacher/index.html">선생님페이지</a></li>
            <li><a href="/student/index.html">수강생페이지</a></li>
          </ul>
        </nav>
        
        <nav class="menu text-menu">
          <h1>알림관리</h1>
          <ul>            
            <li><a href="/admin/board/notice/list.html">공지사항</a></li>       
          </ul>
        </nav>
                
      </aside>
      <!-- --------------------------- main --------------------------------------- -->
      
      <!-- content 부분 -->
      
  <main class="main">
    <h2 class="main title">관리자홈</h2>
    
    <div class="breadcrumb">
      <h3 class="hidden">breadlet</h3>
      <ul>
        <li>home</li>
        <li>마이페이지</li>
        <li>홈</li>
      </ul>
    </div>
    
    <div class="margin-top first">
    
    </div>
    
  </main>
      
    </div>
  </div>
  <!-- ------------------- <footer> --------------------------------------- -->
  

        <footer id="footer">
            <div class="content-container">
                <h2 id="footer-logo"><img src="/assets/images/logo-footer.png" alt="회사정보"></h2>
    
                <div id="company-info">
                    <dl>
                        <dt>주소:</dt>
                        <dd>서울특별시 </dd>
                        <dt>관리자메일:</dt>
                        <dd>admin@newlecture.com</dd>
                    </dl>
                    <dl>
                        <dt>사업자 등록번호:</dt>
                        <dd>111-11-11111</dd>
                        <dt>통신 판매업:</dt>
                        <dd>신고제 1111 호</dd>
                    </dl>
                    <dl>
                        <dt>상호:</dt>
                        <dd>뉴렉처</dd>
                        <dt>대표:</dt>
                        <dd>홍길동</dd>
                        <dt>전화번호:</dt>
                        <dd>111-1111-1111</dd>
                    </dl>
                    <div id="copyright" class="margin-top">Copyright ⓒ newlecture.com 2012-2025 All Right Reserved.
                        Contact admin@newlecture.com for more information</div>
                </div>
            </div>
        </footer>
</body>
</html>