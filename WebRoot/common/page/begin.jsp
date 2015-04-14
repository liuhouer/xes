<pg:pager
    items="${pagination.totalRecord}"
    maxPageItems="${pagination.range}"
    maxIndexPages="${pagination.maxIndexPages}"
    isOffset="<%= true %>"
    url="do.jsp"
    export="currentPageNumber=pageNumber"
    scope="request">   
  <pg:param name="range"/>
  <pg:param name="maxIndexPages"/>

