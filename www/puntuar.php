<?php
    require("conection.php");
    
    $id_usuario = $_POST['id_usuario'];
    $id_local = $_POST['id_local'];
    $puntuacion = $_POST['puntuacion'];

    $sql = "INSERT INTO PUNTUACIONES (id_usuario, id_local, puntos) VALUES ('$id_usuario', '$id_local', '$puntuacion') ON DUPLICATE KEY UPDATE puntos = '$puntuacion'";
    $mysqli->query($sql);


    echo "0";

    //echo "hola";
?>