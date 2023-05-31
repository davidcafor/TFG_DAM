import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

import '../models/models.dart';



class TiendaProvider with ChangeNotifier {

  late String ip;
  late String port;
  final apiToken = "a93E3cr6vpO-QMXYP_eJdrkujJR2OgUOQ5nsXa21";

  Future<void> initializeSettings() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    ip = prefs.getString('ip') ?? "";
    port = prefs.getString('port') ?? "";
  }

  Future<List<Tienda>> listaTiendas() async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Tiendas/views/Tiendas');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<Tienda> listaTiendas = TiendaResponse.fromJson(jsonDecode(response.body)).list;

    return listaTiendas;
  }

  Future<List<Tienda>> listaTiendasByProducto({required int idProducto}) async {

    await initializeSettings();
    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Tiendas/views/Tiendas?where=(IdProducto,eq,$idProducto)');
    var headers = {
      'xc-token' : apiToken
    };
    var response = await http.get(url, headers: headers);

    List<Tienda> listaTiendasByProducto = TiendaResponse.fromJson(jsonDecode(response.body)).list;

    return listaTiendasByProducto;
  }
}