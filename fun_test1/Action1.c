Action1()
{
	char le[6]="wangle";
	char wang[]="abcdefg";
	
	lr_log_message("%s",le);
	lr_log_message("%s",wang);
	lr_log_message("%d",sizeof(wang));
	lr_log_message("%d",strlen(wang));

	return 0;
}