import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:modus/models/producto.dart';
import 'package:http/http.dart' as http;

class ProductoProvider with ChangeNotifier {

  final ip = "192.168.68.57"; //CAMBIAR A GUARDADAS
  final port = "8090";
  final apiToken = "a93E3cr6vpO-QMXYP_eJdrkujJR2OgUOQ5nsXa21";

  Future<List<ListElement>> listaProductos() async {

    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<ListElement> listaProductos = Producto.fromJson(jsonDecode(response.body)).list;

    return listaProductos;
  }
}