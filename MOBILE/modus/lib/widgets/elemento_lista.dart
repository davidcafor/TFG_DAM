import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../models/models.dart';

class ElementoLista extends StatefulWidget {

  final Producto producto;

  ElementoLista({Key? key, required this.producto})
      : super(key: key);

  @override
  State<ElementoLista> createState() => _ElementoListaState();
}

class _ElementoListaState extends State<ElementoLista> {

  String? ip;

  @override
  void initState() {
    super.initState();
    getIpFromSharedPreferences();
  }

  Future<void> getIpFromSharedPreferences() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      ip = prefs.getString('ip');
    });
  }

  @override
  Widget build(BuildContext context) {

    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(10),
      ),
      child: Container(
        margin: EdgeInsets.all(10),
        height: 150,
        child: Row(
          children: [
            Expanded(
              flex: 2,
              child: ip != null && widget.producto.imagen != null
                  ? FadeInImage.assetNetwork(
                placeholder: 'assets/images/default.png',
                image: 'http://$ip${widget.producto.imagen}',
                fit: BoxFit.cover,
              )
                  : Image.asset(
                'assets/images/default.png',
                fit: BoxFit.cover,
              ),
            ),
            Expanded(
              flex: 3,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 15),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(bottom: 10),
                      child: Text(
                        widget.producto.nombre,
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 20,
                        ),
                      ),
                    ),
                    Text(
                      widget.producto.descripcion,
                      maxLines: 3,
                    ),
                    Text(
                      'Precio: ${widget.producto.precio}€',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
