<?php
    require("conection.php");
    

    $nombre = $_POST['nombre'];
    $ubicacion = $_POST['ubicacion'];
    $descripcion = $_POST['descripcion'];
    $propietario = $_POST['propietario'];




    if (empty($nombre) || empty($ubicacion) || empty($descripcion) || empty($propietario)){
        echo "-1";
    }
    else {
        $sql = "INSERT INTO LOCALES (nombre_local, ubicacion, descripcion, owner_local) VALUES ('$nombre', '$ubicacion', '$descripcion', '$propietario')";
        $mysqli->query($sql);

        $sql = "SELECT MAX(id_local) as id_local from LOCALES";
        $resultado = $mysqli->query($sql);

        $res = $resultado->fetch_assoc();
        $idLocal = $res['id_local'];

        $sql = "INSERT INTO PUNTUACIONES (id_usuario, id_local, puntos) VALUES ('$propietario', '$idLocal', '0')";
        $mysqli->query($sql);

        echo "0";
    }

    //echo "hola";
?>