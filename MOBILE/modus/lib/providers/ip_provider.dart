import 'package:flutter/cupertino.dart';
import 'package:shared_preferences/shared_preferences.dart';

class IpProvider extends ChangeNotifier {
  String? ip;

  void getIpFromSharedPreferences() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    ip = prefs.getString('ip');
    notifyListeners();
  }
}