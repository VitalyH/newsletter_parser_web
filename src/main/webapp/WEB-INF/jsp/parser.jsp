<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<div class="container">
  h==Header / p==Paragraph / s==Separator
  <br>
  ls==List Start / ll==List / le==List End
  <br>
  fl==Photo Left / fr==Photo Right
  <br>
  r==Reporter / sign==Signature

    <form:form method="post" >
        <textarea class="form-control" name="inputField" type="submit" rows="17" style="width: 60%">${inputField}</textarea>
        <button type="submit" class="btn btn-success p=1"/>Run</button>
        <textarea class="form-control" name="outputField" rows="17" style="width: 60%">${outputField}</textarea>
     </form:form>

     <a type="button" href="/clear-all" class="btn btn-warning"/>Clear all</a>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>