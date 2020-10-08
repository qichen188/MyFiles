searchPython()
{

	web_add_cookie("Hm_lpvt_3eec0b7da6548cf07db3bc477ea905ee=1597459591; DOMAIN=www.runoob.com");

	web_url("www.runoob.com_2", 
		"URL=https://www.runoob.com/?s=python", 
		"Resource=0", 
		"RecContentType=text/html", 
		"Referer=https://www.runoob.com/?s=java", 
		"Snapshot=t72.inf", 
		"Mode=HTTP", 
		LAST);

	web_concurrent_start(NULL);

	web_url("weipayimg.png_3", 
		"URL=https://static.runoob.com/images/dashang/weipayimg.png", 
		"Resource=1", 
		"RecContentType=image/png", 
		"Referer=https://www.runoob.com/?s=python", 
		"Snapshot=t73.inf", 
		LAST);

	web_url("style.css_3", 
		"URL=https://www.runoob.com/wp-content/themes/runoob/style.css?v=1.156", 
		"Resource=1", 
		"Referer=https://www.runoob.com/?s=python", 
		"Snapshot=t74.inf", 
		LAST);

	web_concurrent_end(NULL);

	web_concurrent_start(NULL);

	web_url("hm.js_3", 
		"URL=https://hm.baidu.com/hm.js?3eec0b7da6548cf07db3bc477ea905ee", 
		"Resource=1", 
		"Referer=https://www.runoob.com/?s=python", 
		"Snapshot=t75.inf", 
		LAST);

	web_url("cd-icon-close.svg_3", 
		"URL=https://www.runoob.com/wp-content/themes/runoob/assets/img/cd-icon-close.svg", 
		"Resource=1", 
		"RecContentType=image/svg+xml", 
		"Referer=https://www.runoob.com/?s=python", 
		"Snapshot=t76.inf", 
		LAST);

	web_url("hm.gif_3", 
		"URL=https://hm.baidu.com/hm.gif?cc=1&ck=1&cl=24-bit&ds=1920x1080&vl=962&et=0&fl=32.0&ja=1&ln=zh-cn&lo=0&lt=1597459548&rnd=752717419&si=3eec0b7da6548cf07db3bc477ea905ee&su=https%3A%2F%2Fwww.runoob.com%2F%3Fs%3Djava&v=1.2.75&lv=3&sn=43988&r=0&ww=1920&ct=!!&tt=python%20%E7%9A%84%E6%90%9C%E7%B4%A2%E7%B5%90%E6%9E%9C", 
		"Resource=1", 
		"RecContentType=image/gif", 
		"Referer=https://www.runoob.com/?s=python", 
		"Snapshot=t77.inf", 
		LAST);

	web_concurrent_end(NULL);

	return 0;
}
