<?php
    require("conection.php");
    
    $id_local = $_POST['id_local'];

    $sql = "SELECT AVG(puntos) as puntuacion FROM PUNTUACIONES where id_local='$id_local'";
    $resultado = $mysqli->query($sql);

    $res = $resultado->fetch_assoc();

    echo round($res['puntuacion'], 2);
?>