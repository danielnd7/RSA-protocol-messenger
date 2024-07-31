import pickle
import os
try: # comprocbacioón de la libreria gmpy2 
    import gmpy2  # para elevar potencias
    print('Las librerias se han cargado correctamente')
except ImportError:
    print('ERROR: La libreria gmpy2 no ha podido ser cargada')

# carpeta_compartida = r'\\DESKTOP-76BTFEF\Users\Public\WORKING_FOLDER' # hay que ajustar su direccion para cada pc !
# contactos = os.path.join(carpeta_compartida, 'Contactos.txt')
print()
print('  #####   #####     #####        #####')
print(' #        #    #   #     #       #     #')
print(' #        #    #        #   ###  #      # ')
print(' #        #####       #          #     #  ')
print('  #####   #        #######       #####') 
print()
print('------------------------------------------')

# comprobación si existe el archivo con la dirección de la carpeta compartida
if os.path.exists("shared_folder.bin"):   
    with open(f'shared_folder.bin', 'rb') as file:
            carpeta_compartida = pickle.load(file)   
    file.close()

else: # si no existe shared_folder.bin, pregunta la dirección 
    print('Bienvenido a CP2-D! Para empezar a usar la')
    print('aplicación debe de intoducir la dirección')
    print('de una carpeta compartida')
    carpeta_compartida = input('Dirección: ')
    # crea el archivo con la dirección de la carpeta compartida
    with open(f'shared_folder.bin', 'wb') as file: 
            pickle.dump(carpeta_compartida, file)
    file.close()

print(f'La direccion de la carpeta compartida:') # coprobación (para el usuario)
print(f'{carpeta_compartida}')

# comprobacion si existe la lista de contactos en la carpeta compartida
contactos = os.path.join(carpeta_compartida, 'Contactos.txt')
if os.path.exists(contactos):
    None
else: # si no existe, se crea
    with open(contactos, 'w') as file:
        contactos = os.path.join(carpeta_compartida, 'Contactos.txt')
    file.close()


while True:
    print()
    print('Que quiere hacer?')
    what_to_do = input('Cifrar(1) / Descifrar(2) / Terminar(3): ')
    
    if what_to_do == '1': ##### CIFRADO ############### CIFRADO ################ CIFRADO ###########################
        if os.path.getsize(contactos) == 0: # si la lista de contactos está vacia no podrá cifrar (no hay para quien)
            print('La lista de contactos esta vacía.') 
        else:   # si no lo está
            print('Los contactos disponibles: ')
            with open(contactos, 'r') as file: # leer la lista de contactos
                list_users = file.readlines()
                for user in list_users: # sacar solo el numero y el nomre de cada usuario y imprimirlos
                    user = user.split()
                    user_name = str(user[0]) + ' ' +  str(user[1]) 
                    print(user_name)
            file.close()
        
            print('¿A quien quiere mandar su mensaje?')
            user_number = input("(introduce el numero del usuario): ")  # Selección del destinatario 
            with open(contactos, 'r') as file: # encuentrar las claves publicas del usuario necesario en la lista
                users = file.readlines()
                user = users[int(user_number) - 1] # encuentrar el numero de usuario
                user = user.split()
                n = int(user[2]) # obtener las claves de usuario
                e = int(user[3])
            file.close()
            # se establece la direccion y el modelo del mensaje cifrado
            encoded_file = os.path.join(carpeta_compartida, f'encoded_message_{user_number}.bin')
            print(f'Introduce su mensaje para {user[1]}: ')
            text = input()
            
            ########################### CIFRADO (codificacion y preparacion) -----------------------------------------------------------------

            encoded_text = text.encode('utf-8')  # convertimos el text en una lista de bytes
            encoded_text_list = list(encoded_text) # convertimos la lista de bytes en una lista de numeros entero ademas de base 10

            # AÑADIR CEROS AL FINAL:
            number_bytes = len(encoded_text_list) # recuento de los elemtos de la lista
            rest = number_bytes % 4 # comprobamos si es multiplo de 2
            if rest != 0:  # si no le añadimos ceros hasta que lo sea
                rest = 4 - rest # 'rest' ahora es la cantidad de ceros que faltan para que sea multiplo de 4
                while rest > 0: # añadimos ceros
                    encoded_text_list.append(int(0))
                    rest = rest - 1
            # print(encoded_text_list) # COMPROBACION

            # dividir en bloques de 4 bytes y formar un numero con cada bloqe:
            list_numbers = []
            for i in range(0, len(encoded_text_list), 4):
                bloque = encoded_text_list[i:(i + 4):1]
                # print('Bloque: ', bloque) # COMPROBACION
                num_bloque = int(bloque[0]) * 2 ** 24 + int(bloque[1]) * 2 ** 16 +  int(bloque[2]) * 2 ** 8 + int(bloque[3]) 
                list_numbers.append(num_bloque)
            # print('lista', list_numbers) # COMPROBACION
            # exportar la lista de numeros al archivo binario:  
            with open(f'encoded_message_{user_number}.bin', 'wb') as file:
                pickle.dump(list_numbers, file)
            file.close()
            
            ############################  CIFRADO (calculo) --------------------------------------------------------------------

             # importar la lista de numeros del archivo binario: 
            with open(f'encoded_message_{user_number}.bin', 'rb') as file:
                list_numbers = pickle.load(file)
            file.close()
            # cifrar el texto (codificado):
            encrypted_text_list = [] # lista de numeros ya CIFRADOS
            for number in list_numbers: # cifrar cada numero de la lista
                number = gmpy2.powmod(number, e, n) # eleva 'number' a petencia 'e' en modulo 'n'
                encrypted_text_list.append(number)
            # print('texto encriptado: ', encrypted_text_list) # COMPROBACION

            # crea el archivo cifrado en la carpeta compartida:
            encrypted_message = os.path.join(carpeta_compartida, f'encrypted_message_{user_number}.bin') 
            with open(encrypted_message, 'wb') as file: # Exportar el mensaje cifrado a la carpeta compartida
                pickle.dump(encrypted_text_list, file)
            file.close()

            print ('¡Mensaje fue cifrado con éxito!')
        
################################################################################################################################

    elif what_to_do == '2': ######## DESCIFRADO ################ DESCIFRADO ################ DESCIFRADO ########
        # comprobar si ya existe el usuario creado:
        if os.path.exists('private_key.bin'):
            my_user = []
            with open('private_key.bin', 'rb') as file: # si existe se cogen las claves (privada)
                my_user = pickle.load(file)
                # print(my_user) # COMPROBACION
                user_number = my_user[0]
                n = my_user[1]
                d = my_user[2]
            file.close()
            # se determina la direccion y el modelo del mensaje para este usuario
            encoded_file = os.path.join(carpeta_compartida, f'encrypted_message_{user_number}.bin')
            
            # comprobar si hay mensajes para ti:
            if os.path.exists(encoded_file) == False:
                print('No tiene mensajes nuevos.')
            else: 
                with open(encoded_file, 'rb') as file: # Imortar el mensaje cifrado de la carpeta compartida (si es que hay)
                    encoded_text_list = pickle.load(file)
                list_numbers = []

                ############# DESCIFRADO (calculo) ---------------------------------------------------------------------

                for number in encoded_text_list: # descifrar uno por uno
                    number = gmpy2.powmod(number, d, n)
                    list_numbers.append(number) 
                # print('Descifraso: ', list_numbers) # COMPROBACION

                with open('encoded_message.bin', 'wb') as file:
                    pickle.dump(list_numbers, file)
                file.close()

                #############################################################

                with open('encoded_message.bin', 'rb') as file:
                    list_numbers = pickle.load(file)
                file.close()

                # separar cada bloque en 4 numeros (bytes):
                encoded_text_list = []
                #for number in list_numbers:
                #    number_1 = number // 2 ** 24 # coge el bloque 1
                #    k = number % 2 ** 24 # se elimina el bloque 1 del numero grande 
                #    number_2 = k // 2 ** 16 # coge el bloque 2
                #    k = k % 2 ** 16 # se elimina el bloque 2 del numero grande
                #    number_3 = k // 2 ** 8 # coge el bloque 3
                #    number_4 = k % 2 ** 8 # coge el bloque 4

                #___Lo mismo que arriba pero optimizado___#
                for number in list_numbers:
                    number_1, k = divmod(number, 2 ** 24) # divide el número por 2^24 y obtiene el cociente y el resto
                    number_2, k = divmod(k, 2 ** 16) # divide k por 2^16 y obtiene el cociente y el resto
                    number_3, number_4 = divmod(k, 2 ** 8) # divide k por 2^8 y obtiene el cociente y el resto

                    encoded_text_list.append(number_1)
                    encoded_text_list.append(number_2)
                    encoded_text_list.append(number_3)
                    encoded_text_list.append(number_4)
                # print(encoded_text_list) # COMPROBACION
                
                # eliminacion de ceros al final de la lista: 
                encoded_text_list.reverse() # invertimos la lista
                for i in range(len(encoded_text_list)): 
                    if encoded_text_list[i] != 0: # *cuado llegamos a un elemento distinto de cero:
                        encoded_text_list = encoded_text_list[i:] # nueva lista parte de alli (fin)
                        break 
                encoded_text_list.reverse() # invertimos la lista otra vez
                # print('Sin ceros:', encoded_text_list) # COMPROBACION
                encoded_text = bytes(encoded_text_list) # convertimos la lista de numeros a una lista de bytes
                text = encoded_text.decode('utf-8') # la decodificamos
                print('Mensaje descifrado es:')
                print(text)
        
        else:           #####################################################################################################
            create = input('No tiene una cuenta. Quire crear una? no(0) / si(1): ')
            if create == '1':   ################################# CREACION DE USUARIO #######################################
                while True:
                    name = input('Introduce su nombre (sin espacios): ')
                    if os.path.getsize(contactos) == 0: 
                        break
                    else: # comprobar si ya existe este nombre:
                        names = [] # lista de nombres ya existentes
                        with open(contactos, 'r') as file: # importar los que existen
                            users = file.readlines()
                            for user in users:
                                user = user.split()
                                names.append(user[1])
                        file.close()
                        if name in names:
                            print('Este nombre ya está ocupado')
                        else:
                            break

                print('Se está creando nuevo usuario...')
                print('Esto puede tardar un rato (hasta 30 min)')
                print('No cierre el programa ni apague el ordenador!')

                ########################### CREACION (calculo) ----------------------------------------------------------

                # n = 97187 * 46633
                # e = 3751768663
                # d = 129488311

                def es_primo(n):
                    """comprueba si un numero (n) es primo"""
                    div = 2 # divisor
                    paso = 1 # paso que se le va a sumar al divisor
                    div_maximo = n # divisor maximo
                    primo = True # suponenos que es primo
                    while (div < div_maximo) and (primo == True) and (n > 0):
                        resto = n % div  # el resto de la division por un numero que no sea el dado o 1
                        div = div + paso  # 1er divisor es "2" y va aumentando hasta "n - 1"
                        if div == 3 and resto != 0: # si no se divide por '2', va probando solo impares
                            paso = 2
                            div_maximo = (n + 1) / 2 # si no se divide por '2', probar hasta la mitad (si fuera 13, lo divide hasta llegar a 7)
                        if resto == 0:
                            primo = False
                    return(primo)

                import time # para poder cronometrar
                start_time = time.time() # empezar a cronometrar
                # Escoger 2 primos de 5 digitos:
                import random
                while True: # un bucle infinito (busca 'p' y 'q')
                    p = random.randint(10007, 99999) 
                    q = random.randint(10007, 99999)
                    if ((p * q) >= (2 ** 32)) and (es_primo(p) == True) and (es_primo(q) == True):
                        break
                # print(p, q) # COMPROBACION
                n = p * q
                fi = (p - 1) * (q - 1)
                # print('fi: ', fi) # COMPROBACION
                print('....')
                while True: # busca 'e' ( < fi y primo)
                    e = random.randint(3121, fi) # 3121 es un numero primo MINIMO que puede ser 'e'
                    if (es_primo(e) == True) and (e < fi):
                        break
                # print('e: ', e) # COMPROBACION
                print('....')
                d = 0
                while d < fi: # busca 'd' (e * d = 1 mod fi)
                    if (d * e) % fi == 1:
                        break
                    else:
                        d += 1
                finish_time = time.time() # terminar de cronometrar
                # print('d: ', d) # COMPROBACION
                
                public_key = [n, e]

                ########################## FIN DE CREACIÓN ---------------------------

                # guardar la clave publica en Contactos.txt:
                if os.path.getsize(contactos) == 0: # si la lista está vacía
                    user_number = 1 # se cea el primer usuario
                    user = [str(user_number), name, str(n), str(e)]
                    user = ' '.join(user)
                    with open(contactos, 'w') as file: # se añade a la lista
                        file.write(user) 
                    file.close() 
                else:  # se encuentra el numero del último usuario de la lista (si es que existen)
                    with open(contactos, 'r') as file:
                        users = file.readlines()  
                    file.close()
                    # se coge el numero de usuario siguiendo el orden de la lista
                    last_user = users[-1]
                    last_number = last_user[0]
                    user_number = int(last_number) + 1 
                    user = [str(user_number), name, str(n), str(e)] # se forma el usuario
                    user = ' '.join(user)

                    with open(contactos, 'a') as file: # se añade al final el nuevo usuario
                        file.write('\n' + user)
                    file.close()    

                # guardar la clave privada en un fichero (también se guarda el numero del mismo usuario):
                private_key = [user_number, n , d] # para luego poder destinguir mensajes dirigidos a este usuario
                with open('private_key.bin', 'wb') as file: 
                    pickle.dump(private_key, file)
                file.close()   
                
                d = e = n =  fi = p = q = None # borrar los restos
                # print(f'El tiempo tardado en el calculo: {round(((finish_time - start_time) / 60), 2)} min') # Cuanto tiempo tardó el calculo
                print('Creación del usuario finalizada con éxito!')

    elif what_to_do == '3':
        break
    else:
        print('Comando no reconocido.')