/// @description Insert description here
// You can write your code in this editor
show_debug_message("alarm triggered");
lap += 1;
networkSendString(clientSocket,"test string: " + string(lap));
alarm_set(0, lap*60);