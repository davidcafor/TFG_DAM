import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../models/producto.dart';

class ElementoLista extends StatelessWidget {

  final Producto producto;

  ElementoLista({Key? key, required this.producto, }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(15),
      height: 200,
      child: Row(
        children: [
          Expanded(
            flex: 2,
            child: producto.imagen != null
                ? Image.network(
              'http://192.168.68.57' + producto.imagen!,
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
              padding: const EdgeInsets.symmetric(horizontal: 30.0),
              //child: Padding(
                //padding: const EdgeInsets.only(bottom: 30.0),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(bottom: 15.0),
                      child: Text(
                        producto.nombre,
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 20,
                        ),
                      ),
                    ),
                    //Expanded(
                      //child: Padding(
                    Padding(
                        padding: const EdgeInsets.only(bottom: 10.0),
                        child: Text(
                          producto.descripcion,
                          maxLines: 3,
                        ),
                      ),
                    //),
                    Text(
                      'Precio: ${producto.precio}€',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ),
         // ),
        ],
      ),
    );
  }
}