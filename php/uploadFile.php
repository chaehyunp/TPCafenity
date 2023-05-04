<?php
    header("Content-Type:text/html; charset=utf-8");

    //사용자가 File을 보내면 실제 파일 데이터들은 임시저장소(temp)에 임시로 저장되며
    //이 php문서에는 File의 정보만 전달됨
    //그 정보들은 $_FILES[]에 저장됨
    $file=$_FILES["img"];

    //$file 변수도 배열임. 즉.$file 배열 변수 안에 전송된 파일에 대한 여러 정보(5개)가 있음
    $srcName=$file["name"]; //원본파일명
    $type=$file["type"]; //파일타입
    $size=$file["size"]; //파일크기
    $tmpName=$file["tmp_name"]; //파일데이터가 저장도니 임시저장소의 파일주소(위치)
    $error=$file["error"]; //에러정보

    //제대로 왔는지 확인해보기위해 출력(응답)
    echo "$srcName <br> $type <br> $size <br> $tmpName <br> $error";

    //정보가 잘 확인되었다면 분명 서버에 이미지 파일이 전송된 것
    //하지만 이 파일데이터는 임시저장소에 저장되어 있어서 곧 삭제됨
    //온전히 업로드를 하기위해서 임시저장소에 있는 파일[$tmpName]을 영구히 사라지지 않는 본인 폴더(html폴더)쪽으로 이동해야함
    //이 php문서가 있는 폴더에 [upload]라는 폴더를 새로 만들어서 이 폴더 안으로 이동시키기
    //폴더는 알아서 만들지 못함 미리 준비해놓을것!
    $dstName="./upload/".date("YmdHis").$srcName;

    //임시저장소($tmpName)에 있는 파일을 원하는 위치($dstName)으로 이동
    move_uploaded_file($tmpName, $dstName);
    if($result) echo "success uplosd";
    else echo "fail upload";
?>