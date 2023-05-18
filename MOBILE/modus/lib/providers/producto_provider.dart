import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:modus/models/producto.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class ProductoProvider with ChangeNotifier {

  late String ip;
  late String port;
  final apiToken = "a93E3cr6vpO-QMXYP_eJdrkujJR2OgUOQ5nsXa21";

  Future<void> initializeSettings() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    ip = prefs.getString('ip') ?? "";
    port = prefs.getString('port') ?? "";
  }

  Future<List<ListElement>> listaProductos() async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<ListElement> listaProductos = Producto.fromJson(jsonDecode(response.body)).list;

    return listaProductos;
  }

  Future<List<ListElement>> listaProductosByID({required idProducto}) async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos?where=(Productos.Id,eq,$idProducto)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<ListElement> listaProductosByID = Producto.fromJson(jsonDecode(response.body)).list;

    return listaProductosByID;
  }
}