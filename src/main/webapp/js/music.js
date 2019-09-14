k=0;
function no_off(){
	if(k==0){
		document.getElementById('music').pause();
		document.getElementById('imgkey').src="images/播放.png";
		k=1;
		return false;
	}else{
		document.getElementById('music').play();
		document.getElementById('imgkey').src="images/暂停.png";
		k=0;
		return false;
	}
}	