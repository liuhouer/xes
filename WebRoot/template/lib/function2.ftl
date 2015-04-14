<div class="cen_list_page"><span title="记录总数" style="padding: 0px 4px;">记录总数: <strong>${pagination.totalRecord}</strong></span>
<span style="padding: 0px 4px;"><strong>${currentPage}</strong>/<strong>${totalPage}</strong>页</span>
        <span style="padding: 0px 4px;"><a href="${first}">首页</a></span>
        <#setting number_format="0.######"/>
        <#if totalPage<=10>
        	<#if totalPage!=0>
		        <#-- 1...10 -->
		        <#list 1..totalPage as i>
				  <a href="<#if (i == 1)>${(first)}<#else>${(path)!}/list_${i}.html</#if>" title="第${i}页" style="padding:0px 2px;"><#if currentPage = i><strong style="color:red">${i}</strong><#else>${i}</#if></a>
				</#list> 
        	</#if>			
		<#elseif totalPage gt 10>	
			<#assign tempStart=(currentPage/10?int)>
			<#if currentPage != (totalPage/10?int)>
				<#if (currentPage >= 10)>
					<#if ((currentPage - 10 - (currentPage%10) + 1)==1)>
						<span style="padding: 0px 4px;"><a href="${first}">前十页</a></span>
					<#else>
						<span style="padding: 0px 4px;"><a href="${(path)!}/list_${(currentPage - 10 - (currentPage%10) + 1)}.html">前十页</a></span>
					</#if>
				</#if>
				<#-- middle page -->
				<#assign outerPageNumber=0>	
				<#if (currentPage % 10 == 0)>
					<#assign outerPageNumber=(currentPage / 10)?int - 1>	
				<#else>
					<#assign outerPageNumber=((currentPage / 10)?int)>	
				</#if>
				
				<#list 1..10 as i>
					<#assign pageLabel=outerPageNumber * 10 + (i)>	
					<#if pageLabel <= totalPage>
					<a href="<#if (pageLabel == 1)>${(first)}<#else>${(path)!}/list_${pageLabel}.html</#if>"  title="第${pageLabel}页" style="padding:0px 2px;"><#if currentPage=pageLabel><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>				
					</#if>
				</#list>
				<#-- if the page is 10*n -->
				<#if (((outerPageNumber + 1)*10) < totalPage)>
					<span style="padding: 0px 4px;"><a href="${(path)!}/list_<#if (currentPage % 10 == 0)>${(currentPage/10)?int * 10 + 1}<#else>${((currentPage/10)?int+1) * 10 + 1}</#if>.html">后十页</a></span>
				</#if>
			<#else>
				<#-- add previous.gif pic to return front 10 page -->
				<#if (currentPage >= 10)>
					<#if ((currentPage -10 - (currentPage%10) + 1)==1)>
						<span style="padding: 0px 4px;"><a href="${first}">前十页</a></span>
					<#else>
						<span style="padding: 0px 4px;"><a href="${(path)!}/list_${(currentPage -10 - (currentPage%10) + 1)}.html">前十页</a></span>
					</#if>
				</#if>
				<#-- last page -->
				<#list 1..(totalPage%10)?int as i>
					
					<#if (currentPage <= 10)>
					<#assign pageLabel=(i)>	
					<#else>
					<#assign pageLabel=(currentPage + i - (currentPage%10))>	
					</#if>
		
					<#if pageLabel <= totalPage>
					
					<a href="<#if (pageLabel == 1)>${(first)}<#else>${(path)!}/list_${pageLabel}.html</#if>"  title="第${pageLabel}页" style="padding:0px 2px;"><#if currentPage=pageLabel><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
					</#if>
				</#list>				
			</#if>
		</#if>				
		<!-- <span style="padding: 0px 4px;"><img src="../images/next.gif" alt="后十页"/></span> -->
		<span style="padding: 0px 4px;"><a href="<#if (totalPage == 1)>${(first)}<#else>${(path)!}/list_${(totalPage)}.html</#if>" >尾页</a></span></div>