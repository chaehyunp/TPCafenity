<?php
    header('Content-Type:text/plain; charset=utf-8');

    $nick = $_POST['nick'];

    //MySQL DB [account_email]
    $db = mysqli_connect('localhost', 'testhue96', 't1e2s3t4!', 'testhue96');
    mysqli_query($db, 'set names utf8');

    $sql = "SELECT count(*) as total FROM account_email WHERE nick ='$nick'";
    $result = mysqli_query($db,$sql);

    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
    echo $row['total'];

    mysqli_close($db);
?>