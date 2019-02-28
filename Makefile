moveFile: sms_dump_*.txt
	mv sms_dump_*.txt sms.txt
	cp sms.txt app/src/main/assets
	rm sms.txt
	
	
	