import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:modus/models/producto.dart';
import 'package:http/http.dart' as http;
import 'package:modus/models/producto_response.dart';
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

  Future<List<Producto>> listaProductos() async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<Producto> listaProductos = ProductoResponse.fromJson(jsonDecode(response.body)).list;

    return listaProductos;
  }

  Future<List<Producto>> listaProductosByID({required idProducto}) async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos?where=(Productos.Id,eq,$idProducto)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<Producto> listaProductosByID = ProductoResponse.fromJson(jsonDecode(response.body)).list;

    return listaProductosByID;
  }

  Future<List<Producto>> listaProductosQuery(String query) async {
    await initializeSettings();

    String encodedQuery = Uri.encodeComponent('%$query%'); //para que no me pille %CA en camiseta

    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos?where=(Nombre,like,$encodedQuery)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    print(url);

    List<Producto> listaProductosQuery = ProductoResponse.fromJson(jsonDecode(response.body)).list;

    return listaProductosQuery;
  }
}