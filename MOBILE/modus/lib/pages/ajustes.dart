import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

class Ajustes extends StatefulWidget {
  const Ajustes({Key? key}) : super(key: key);

  @override
  State<Ajustes> createState() => _AjustesState();
}

class _AjustesState extends State<Ajustes> {

  final TextEditingController _ipController = TextEditingController();
  final TextEditingController _portController = TextEditingController();
  final apiToken = "a93E3cr6vpO-QMXYP_eJdrkujJR2OgUOQ5nsXa21";
  String _connectionStatus = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Ajustes'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Dirección IP:',
              style: TextStyle(fontSize: 16),
            ),
            TextField(
              controller: _ipController,
              decoration: InputDecoration(hintText: 'Ingrese la dirección IP'),
            ),
            SizedBox(height: 16),
            Text(
              'Puerto:',
              style: TextStyle(fontSize: 16),
            ),
            TextField(
              controller: _portController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(hintText: 'Ingrese el puerto'),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                _saveSettings();
                _testConnection();
              },
              child: Text('Conectar'),
            ),
            SizedBox(height: 16),
            Text(
              _connectionStatus,
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
    );
  }

  //Metodo para cargar valores guardados inicialmente
  void initState(){
    super.initState();
    _loadSavedSettings();
  }

  //Metodo carga sharedPreferences y rellena campos
  void _loadSavedSettings() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String ip = prefs.getString('ip') ?? '';
    String port = prefs.getString('port') ?? '';

    setState(() {
      _ipController.text = ip;
      _portController.text = port;
    });
  }

  //Metodo guardar ip/puerto en sharedPreferences
  void _saveSettings() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString('ip', _ipController.text);
    prefs.setString('port', _portController.text);
  }

  //Metodo de comprobacion de conexion
  Future<void> _testConnection() async {
    String ip = _ipController.text;
    String port = _portController.text;

    if (ip.isEmpty || port.isEmpty) {
      setState(() {
        _connectionStatus = 'IP o puerto nulo';
      });
      return;
    }

    var url = Uri.parse('http://$ip:$port/api/v1/db/data/noco/p_96xp5wgyv3d0fj/Productos/views/Productos');
    var headers = {
      'xc-token' : apiToken
    };

    try {
      var response = await http.get(url, headers: headers);

      if (response.statusCode == 200) {
        setState(() {
          _connectionStatus = 'Conectado';
        });
      } else {
        setState(() {
          _connectionStatus = 'No conectado';
        });
      }
    } catch (e) {
      setState(() {
        _connectionStatus = 'Error de conexión: $e';
      });
    }
  }

}