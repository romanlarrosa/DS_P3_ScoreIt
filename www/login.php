<?php
    
    require("conection.php");

    $usuario = mysqli_real_escape_string($mysqli, $_POST['user']);
    $pass = $_POST['pass'];




    //Verificamos las variables
    $sql = $mysqli->query("SELECT * from USUARIOS where nombre = '$usuario'");
    $result = array();
    if($sql->num_rows > 0) {
        while( $row = $sql->fetch_assoc()) {
            $result[] = $row;
        }
    }

    //Comprobar
    for($i = 0; $i < sizeof($result); $i++){
        if(password_verify($pass, $result[$i]['pass'])){
            echo json_encode($result);
        }
    }
    
    
?>