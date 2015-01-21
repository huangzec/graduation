function preview(oper)         
{  
	if (oper < 10)  {  
		bdhtml=window.document.body.innerHTML;//获取当前页的html代码  
		sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域  
		eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域  
		prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)); //从开始代码向后取html  
  
		prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html  
		window.document.body.innerHTML=prnhtml;  
		window.print();  
	} else {  
		window.print();  
	}  
}  
