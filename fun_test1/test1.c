test1()
{	
//	int y1=0;
//	int y2=0;
//	lr_save_string("123","NumberStr");
//	y1=atoi("123");
//	y1=y1+1;
//	y2=y1+4;
//	lr_output_message("%d",y1);
//	lr_output_message("%d",y2);
//	int result;
//	result=strcmp(lr_eval_string("{User}"),"test1200");
//	if(result==0)
//		lr_save_int(1,"number")
//	;
//	else
//		lr_save_int(8,"number")
//	;
	
	int x=3;
	int i=0;
	int result;
	int result1;
	char *strResult;
	char value[20]="wangle";
	lr_save_string("wangle","value1");
	result=strlen(value);


	lr_param_sprintf("strResult","%d",result,20);
	lr_log_message("%%%%%%%%%: %s",lr_eval_string("{strResult}"));
	
	lr_param_sprintf("strResult1","%d",result1,20);
	lr_log_message("%%%%%%%%%: %s",lr_eval_string("{strResult1}"));
	
//	lr_output_message(result);
	for(i=0;i<10;i++){
		if(result!=6){
			lr_log_message("大于0大于0大于0");
			lr_log_message("大于1大于1大于1");
			lr_log_message("大于2大于2大于2");
		}
		else{
			lr_log_message("小于0小于0小于0");
		}
	}
}
