/* TinySort 1.5.3
* Copyright (c) 2008-2013 Ron Valstar http://tinysort.sjeiti.com/
*
* Dual licensed under the MIT and GPL licenses:
*   http://www.opensource.org/licenses/mit-license.php
*   http://www.gnu.org/licenses/gpl.html
*/
(function(e,c){var h=!1,k=null,o=parseFloat,l=Math.min,m=/(-?\d+\.?\d*)$/g,g=/(\d+\.?\d*)$/g,i=[],f=[],b=function(p){return typeof p=="string"},n=Array.prototype.indexOf||function(r){var p=this.length,q=Number(arguments[1])||0;q=q<0?Math.ceil(q):Math.floor(q);if(q<0){q+=p}for(;q<p;q++){if(q in this&&this[q]===r){return q}}return -1};e.tinysort={id:"TinySort",version:"1.5.2",copyright:"Copyright (c) 2008-2013 Ron Valstar",uri:"http://tinysort.sjeiti.com/",licensed:{MIT:"http://www.opensource.org/licenses/mit-license.php",GPL:"http://www.gnu.org/licenses/gpl.html"},plugin:(function(){var p=function(q,r){i.push(q);f.push(r)};p.indexOf=n;return p})(),defaults:{order:"asc",attr:k,data:k,useVal:h,place:"start",returns:h,cases:h,forceStrings:h,ignoreDashes:h,sortFunction:k}};e.fn.extend({tinysort:function(){var C,B,E=this,s=[],r=[],F=[],G=[],v=0,q,A=[],x=[],y=function(H){e.each(i,function(I,J){J.call(J,H)})},w=function(S,Q){var H=0;if(v!==0){v=0}while(H===0&&v<q){var O=G[v],L=O.oSettings,P=L.ignoreDashes?g:m;y(L);if(L.sortFunction){H=L.sortFunction(S,Q)}else{if(L.order=="rand"){H=Math.random()<0.5?1:-1}else{var R=h,K=!L.cases?a(S.s[v]):S.s[v],J=!L.cases?a(Q.s[v]):Q.s[v];if(!t.forceStrings){var I=b(K)?K&&K.match(P):h,T=b(J)?J&&J.match(P):h;if(I&&T){var N=K.substr(0,K.length-I[0].length),M=J.substr(0,J.length-T[0].length);if(N==M){R=!h;K=o(I[0]);J=o(T[0])}}}H=O.iAsc*(K<J?-1:(K>J?1:0))}}e.each(f,function(U,V){H=V.call(V,R,K,J,H)});if(H===0){v++}}return H};for(C=0,B=arguments.length;C<B;C++){var z=arguments[C];if(b(z)){if(A.push(z)-1>x.length){x.length=A.length-1}}else{if(x.push(z)>A.length){A.length=x.length}}}if(A.length>x.length){x.length=A.length}q=A.length;if(q===0){q=A.length=1;x.push({})}for(C=0,B=q;C<B;C++){var D=A[C],t=e.extend({},e.tinysort.defaults,x[C]),u=!(!D||D==""),p=u&&D[0]==":";G.push({sFind:D,oSettings:t,bFind:u,bAttr:!(t.attr===k||t.attr==""),bData:t.data!==k,bFilter:p,$Filter:p?E.filter(D):E,fnSort:t.sortFunction,iAsc:t.order=="asc"?1:-1})}E.each(function(O,H){var K=e(H),I=K.parent().get(0),J,N=[];for(j=0;j<q;j++){var P=G[j],L=P.bFind?(P.bFilter?P.$Filter.filter(H):K.find(P.sFind)):K;N.push(P.bData?L.data(P.oSettings.data):(P.bAttr?L.attr(P.oSettings.attr):(P.oSettings.useVal?L.val():L.text())));if(J===c){J=L}}var M=n.call(F,I);if(M<0){M=F.push(I)-1;r[M]={s:[],n:[]}}if(J.length>0){r[M].s.push({s:N,e:K,n:O})}else{r[M].n.push({e:K,n:O})}});e.each(r,function(H,I){I.s.sort(w)});e.each(r,function(K,N){var M=N.s.length,J=[],I=M,L=[0,0];switch(t.place){case"first":e.each(N.s,function(P,Q){I=l(I,Q.n)});break;case"org":e.each(N.s,function(P,Q){J.push(Q.n)});break;case"end":I=N.n.length;break;default:I=0}for(C=0;C<M;C++){var O=d(J,C)?!h:C>=I&&C<I+N.s.length,H=(O?N.s:N.n)[L[O?0:1]].e;H.parent().append(H);if(O||!t.returns){s.push(H.get(0))}L[O?0:1]++}});E.length=0;Array.prototype.push.apply(E,s);return E}});function a(p){return p&&p.toLowerCase?p.toLowerCase():p}function d(q,s){for(var r=0,p=q.length;r<p;r++){if(q[r]==s){return !h}}return h}e.fn.TinySort=e.fn.Tinysort=e.fn.tsort=e.fn.tinysort})(jQuery);

