<?php
    
    require("conection.php");

    $usuario = $_POST['user_id'];


    //Verificamos las variables
    $sql = $mysqli->query("SELECT * from USUARIOS where id_usuario = '$usuario'");
    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }


    echo json_encode($result);

    
    
?>