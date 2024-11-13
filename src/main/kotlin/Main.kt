//##################################################################################################
//##################################################################################################
//#####################        COMKOD - утилита для документации кода        #######################
//##################################################################################################
//####  Author:Kulishov I.V.                         ###############################################
//####  Version:0.1.0                                ###############################################
//####  Date:01.10.2024                              ###############################################
//##################################################################################################
//##################################################################################################


package ru.KulishovIV

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.PrintStream
import java.text.Format
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_RESET = "\u001B[0m"
class InputVar(val name:String,val type:String)
var authorConst = "\n"
var lang = "kotlin"


fun main() {
    try {
        val process = ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start()
        process.waitFor()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    var appFlag = true
    val logo =  "\n     ██████╗ ██████╗ ███╗   ███╗██╗  ██╗ ██████╗ ██████╗ \n    ██╔════╝██╔═══██╗████╗ ████║██║ ██╔╝██╔═══██╗██╔══██╗\n    ██║     ██║   ██║██╔████╔██║█████╔╝ ██║   ██║██║  ██║\n    ██║     ██║   ██║██║╚██╔╝██║██╔═██╗ ██║   ██║██║  ██║\n    ╚██████╗╚██████╔╝██║ ╚═╝ ██║██║  ██╗╚██████╔╝██████╔╝\n     ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═════╝\n"
    println(logo)
    println("                                          KulishovIV")
    while (appFlag){
        println("Choose function\n\n1 - Comment\n2 - Sample\n3 - setting\nother - exit\n")
        val func = readlnOrNull()
        if(func=="1"){
            println("choose comment type\n 1 - Programm module\n 2 - Function\n 3 - Class")
            val comType = readlnOrNull()

            if(comType=="1"){
                print("Module name:")

                val module = readLine()
                var author=""
                if(authorConst=="\n") {
                    print("Author:")
                    author = readLine()!!
                } else{
                    author= authorConst
                }
                print("Version:")
                val version = readLine()
                val currentDate = LocalDate.now()
                val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                val date = currentDate.format(dateFormatter)
                val truncatedText = if (module!!.length > 54) module.substring(0, 51)+"..." else module
                val totalPadding = 54 - truncatedText.length
                val paddingStart = totalPadding / 2
                val paddingEnd = totalPadding - paddingStart

                val moduleTemplate = "//##################################################################################################\n"+
                        "//##################################################################################################\n"+
                        "//#####################${truncatedText.padStart(truncatedText.length+paddingStart).padEnd(54)}#######################\n"+
                        "//##################################################################################################\n"+
                        "//####  Author:${author.padEnd(38, ' ')}###############################################\n"+
                        "//####  Version:${version!!.padEnd(37, ' ')}###############################################\n"+
                        "//####  Date:${date!!.padEnd(40, ' ')}###############################################\n"+
                        "//##################################################################################################\n"+
                        "//##################################################################################################\n"
                copyToClipboard(moduleTemplate)
                println("${ANSI_GREEN}The comment has been copied to the clipboard${ANSI_RESET}")
            }
            if(comType=="2"){
                print("Function name:")
                val name = readLine()
                var inputFlag=true
                var inputList = listOf<String>()
                println("Input value")
                while (inputFlag){
                    println("Enter another variable?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Variable name:")
                        val varName = readLine()
                        print("Variable type:")
                        val type = readLine()
                        print("Description:")
                        val description = readLine()
                        inputList+="              $varName:$type - $description"
                    } else inputFlag=false
                }
                println("Output value")
                println("Enter value?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                val outFlag= readLine()
                var outValue = ""
                if(outFlag=="y"){
                    print("Variable name:")
                    val varName = readLine()
                    print("Variable type:")
                    val type = readLine()
                    print("Description:")
                    val description = readLine()
                    outValue="              $varName:$type - $description"
                }
                var output =
                    "//=====================================================================================\n//"+
                            "${if(name!!.length>85) name.substring(0..81)+"...\n" else name.padEnd(84, ' ')}\n"
                if(inputList.size>0){
                    output+="//Input values:\n"
                    for( x in inputList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                if(outValue!=""){
                    output+="//Output values:\n//${if(outValue.length>85) outValue.substring(0..81)+"...\n" else "$outValue\n"}"
                }
                output+="//=====================================================================================\n"
                copyToClipboard(output)
                println("${ANSI_GREEN}The comment has been copied to the clipboard${ANSI_RESET}")
            }
            if(comType=="3"){
                print("Class name:")
                val name = readLine()
                var inputFlag=true
                var inputList = listOf<String>()
                println("Variables:")
                while (inputFlag){
                    println("Enter another variable?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Variable name:")
                        val varName = readLine()
                        print("Variable type:")
                        val type = readLine()
                        print("Description:")
                        val description = readLine()
                        inputList+="              $varName:$type - $description"
                    } else inputFlag=false
                }

                var methodFlag=true
                var methodList = listOf<String>()
                println("Methods:")
                while (methodFlag){
                    println("Enter another method?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Method name:")
                        val varName = readLine()
                        print("Description:")
                        val description = readLine()
                        methodList+="              $varName(): - $description"
                    } else methodFlag=false
                }
                var output =
                    "//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n//"+
                            "${if(name!!.length>85) name.substring(0..81)+"...\n" else name.padEnd(84, ' ')}\n"
                if(inputList.size>0){
                    output+="//Variables:\n"
                    for( x in inputList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                if(methodList.size>0){
                    output+="//Methods:\n"
                    for( x in methodList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                output+="//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                copyToClipboard(output)
                println("${ANSI_GREEN}The comment has been copied to the clipboard${ANSI_RESET}")



            }
        }
        if (func=="2"){
            println("choose sample type\n 1 - Class\n 2 - Function\n 3 - Class file")
            val comType = readlnOrNull()
            if(comType=="1"){
                print("Do you want add anotation? \n" +
                        "${ANSI_GREEN}y-yes${ANSI_RESET}\n" +
                        "${ANSI_RED}n-no${ANSI_RESET}\n")
                val anotIn = readLine()
                var anotation = ""
                if(anotIn=="y"){
                    print("Anotation:")
                    anotation= readLine()!!
                }
                print("Class name:")
                val name = readLine()
                var inputFlag=true
                var inputList = listOf<String>()

                var inputDataList = listOf<InputVar>()
                var inputDataListM = listOf<InputVar>()
                println("Variables:")
                while (inputFlag){
                    println("Enter another variable?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Variable name:")
                        val varName = readLine()
                        print("Variable type:")
                        val type = readLine()
                        print("Description:")
                        val description = readLine()
                        inputList+="              $varName:$type - $description"
                        inputDataList+=InputVar(varName!!,type!!)
                    } else inputFlag=false
                }

                var methodFlag=true
                var methodList = listOf<String>()
                println("Methods:")
                while (methodFlag){
                    println("Enter another methods?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Method name:")
                        val varName = readLine()
                        print("Description:")
                        val description = readLine()
                        methodList+="              $varName(): - $description"
                        inputDataListM+=InputVar(varName!!,description!!)
                    } else methodFlag=false
                }
                var output =
                    "//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n//"+
                            "${if(name!!.length>85) name.substring(0..81)+"...\n" else name.padEnd(84, ' ')}\n"
                if(inputList.size>0){
                    output+="//Variables:\n"
                    for( x in inputList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                if(methodList.size>0){
                    output+="//Methods:\n"
                    for( x in methodList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                output+="//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                if(anotation!="") output+="$anotation\n"

                output+="data class $name("
                var firstFlag=0
                for(x in inputDataList){
                    if(firstFlag!=0) output+=","
                    output+="val ${x.name}:${x.type}"
                    firstFlag++
                }
                output+=")"
                if(inputDataListM.size>0){
                    output+="{\n"
                    for(x in inputDataListM){
                        output+="//=====================================================================================\n"+
                                "//${x.type}\n"+
                                "//=====================================================================================\n"
                        output+="fun ${x.name}(){}\n"
                    }
                    output+="}"
                }

                copyToClipboard(output)
                println("${ANSI_GREEN}The comment has been copied to the clipboard${ANSI_RESET}")



            }
            if(comType=="2"){
                print("Do you want add anotation? \n" +
                        "${ANSI_GREEN}y-yes${ANSI_RESET}\n" +
                        "${ANSI_RED}n-no${ANSI_RESET}\n")
                val anotIn = readLine()
                var anotation = ""
                if(anotIn=="y"){
                    print("Anotation:")
                    anotation= readLine()!!
                }
                print("Function name:")
                val name = readLine()
                var inputFlag=true
                var inputList = listOf<String>()
                class InputVar(val name:String,val type:String)
                var inputDataList = listOf<InputVar>()
                var inputDataListM = listOf<InputVar>()
                println("Input values:")
                while (inputFlag){
                    println("Enter another values?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                    val flagVar = readLine()
                    if(flagVar=="y"){
                        print("Values name:")
                        val varName = readLine()
                        print("Values type:")
                        val type = readLine()
                        print("Description:")
                        val description = readLine()
                        inputList+="              $varName:$type - $description"
                        inputDataList+=InputVar(varName!!,type!!)
                    } else inputFlag=false
                }
                var outData = InputVar("","")
                println("Output value")
                println("Enter value?\n${ANSI_GREEN}y-yes${ANSI_RESET}\n${ANSI_RED}n-no${ANSI_RESET}")
                val outFlag= readLine()
                var outValue = ""
                if(outFlag=="y"){
                    print("Variable name:")
                    val varName = readLine()
                    print("Variable type:")
                    val type = readLine()
                    print("Description:")
                    val description = readLine()
                    outValue="              $varName:$type - $description"
                    outData=InputVar(varName!!,type!!)
                }
                var output =
                    "//=====================================================================================\n//"+
                            "${if(name!!.length>85) name.substring(0..81)+"...\n" else name.padEnd(84, ' ')}\n"
                if(inputList.size>0){
                    output+="//Input values:\n"
                    for( x in inputList){
                        output+="//${if(x.length>85) x.substring(0..81)+"...\n" else "$x\n"}"
                    }
                }
                if(outValue!=""){
                    output+="//Output values:\n//${if(outValue.length>85) outValue.substring(0..81)+"...\n" else "$outValue\n"}"
                }
                output+="//=====================================================================================\n"
                if(anotation!="") output+="$anotation\n"
                output+="fun $name("
                var firstFlag=0
                for(x in inputDataList){
                    if(firstFlag!=0) output+=","
                    output+="${x.name}:${x.type}"

                    firstFlag++
                }
                output+=")"
                if(outData.type!="") output+=":${outData.type}"
                output+="{}\n"
                copyToClipboard(output)
                println("${ANSI_GREEN}The comment has been copied to the clipboard${ANSI_RESET}")


            }
        }
        if(func=="3"){
            println("Choose setting\n1-author\n2-language\nother - exit\n")
            val setobject = readlnOrNull()
            if(setobject=="1"){
                print("Author:")
                authorConst= readlnOrNull().toString()
            }
            if(setobject=="2"){
                println("Choose language\n1-kotlin\nother - exit\n")
                lang= readlnOrNull().toString()
            }
        }

        if(func!="1"&&func!="2"&&func!="3") appFlag=false
    }

}

//=====================================================================================
//Функция сохранения строки в буфер обмена
//Input values:
//              text:String - Сохраняемая строка
//=====================================================================================
fun copyToClipboard_bad(text: String) {
    val stringSelection = StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(stringSelection, null)
}

fun copyToClipboard(text: String) {
    val os = System.getProperty("os.name").lowercase()

    try {
        when {
            os.contains("win") -> {
                // Для Windows используем команду clip
                val process = Runtime.getRuntime().exec("clip")
                OutputStreamWriter(process.outputStream).use { it.write(text) }
                process.outputStream.close()
                process.waitFor()
                //println("Text copied to clipboard on Windows")
            }
            os.contains("mac") -> {
                // Для macOS используем pbcopy
                val process = Runtime.getRuntime().exec("pbcopy")
                OutputStreamWriter(process.outputStream).use { it.write(text) }
                process.outputStream.close()
                process.waitFor()
                //println("Text copied to clipboard on macOS")
            }
            os.contains("nix") || os.contains("nux") -> {
                // Для Linux используем xclip
                val process = Runtime.getRuntime().exec(arrayOf("sh", "-c", "xclip -selection clipboard"))
                OutputStreamWriter(process.outputStream).use { it.write(text) }
                process.outputStream.close()
                process.waitFor()
                //println("Text copied to clipboard on Linux")
            }
            else -> {
                println("Unsupported OS: $os")
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}
