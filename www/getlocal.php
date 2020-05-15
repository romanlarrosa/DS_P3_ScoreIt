<?php
    require("conection.php");
    
    $id_local = $_POST['id_local'];

    $sql = "SELECT * FROM LOCALES where id_local='$id_local'";
    $resultado = $mysqli->query($sql);

    $res = $resultado->fetch_assoc();

    echo json_encode($res);



    //echo "hola";
?>