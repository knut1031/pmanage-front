-- 주석처리 된 행은 삭제됨  
-- ex) '-- '

---- selectMemberJoinList @
-- 멤버 조인 조회
select pm.userId as userId
		 ,uh.loginGubun as loginGubun
from MemberVO pm -- table
		,UserLoginHistoryVO uh
where pm.userId = uh.userId
and pm.userId = :P0


---- selectMemberList @
-- 멤버 조회
from MemberVO pm 
where pm.userId= :P0

---- selectMemberListNative @
-- Native 조회
select *
from pm_user
order by user_id desc