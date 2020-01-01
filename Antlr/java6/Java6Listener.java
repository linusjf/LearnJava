// Generated from Java6.g4 by ANTLR 4.7.2
package java6;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Java6Parser}.
 */
public interface Java6Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Java6Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(Java6Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(Java6Parser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(Java6Parser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(Java6Parser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(Java6Parser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(Java6Parser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(Java6Parser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(Java6Parser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(Java6Parser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(Java6Parser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(Java6Parser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(Java6Parser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(Java6Parser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(Java6Parser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceModifier(Java6Parser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceModifier(Java6Parser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#modifiers}.
	 * @param ctx the parse tree
	 */
	void enterModifiers(Java6Parser.ModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#modifiers}.
	 * @param ctx the parse tree
	 */
	void exitModifiers(Java6Parser.ModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(Java6Parser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(Java6Parser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(Java6Parser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(Java6Parser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(Java6Parser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(Java6Parser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumBody}.
	 * @param ctx the parse tree
	 */
	void enterEnumBody(Java6Parser.EnumBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumBody}.
	 * @param ctx the parse tree
	 */
	void exitEnumBody(Java6Parser.EnumBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstants(Java6Parser.EnumConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstants(Java6Parser.EnumConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(Java6Parser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(Java6Parser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(Java6Parser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(Java6Parser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNormalInterfaceDeclaration(Java6Parser.NormalInterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#normalInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNormalInterfaceDeclaration(Java6Parser.NormalInterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(Java6Parser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(Java6Parser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(Java6Parser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(Java6Parser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(Java6Parser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(Java6Parser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(Java6Parser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(Java6Parser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#member}.
	 * @param ctx the parse tree
	 */
	void enterMember(Java6Parser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#member}.
	 * @param ctx the parse tree
	 */
	void exitMember(Java6Parser.MemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(Java6Parser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(Java6Parser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#methodDeclarationRest}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclarationRest(Java6Parser.MethodDeclarationRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#methodDeclarationRest}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclarationRest(Java6Parser.MethodDeclarationRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericMethodDeclaration(Java6Parser.GenericMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericMethodDeclaration(Java6Parser.GenericMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(Java6Parser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(Java6Parser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(Java6Parser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(Java6Parser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(Java6Parser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(Java6Parser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceMemberDecl}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDecl(Java6Parser.InterfaceMemberDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceMemberDecl}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDecl(Java6Parser.InterfaceMemberDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceMethodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodOrFieldDecl(Java6Parser.InterfaceMethodOrFieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceMethodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodOrFieldDecl(Java6Parser.InterfaceMethodOrFieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceMethodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodOrFieldRest(Java6Parser.InterfaceMethodOrFieldRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceMethodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodOrFieldRest(Java6Parser.InterfaceMethodOrFieldRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#voidMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterVoidMethodDeclaratorRest(Java6Parser.VoidMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#voidMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitVoidMethodDeclaratorRest(Java6Parser.VoidMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaratorRest(Java6Parser.InterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaratorRest(Java6Parser.InterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#interfaceGenericMethodDecl}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceGenericMethodDecl(Java6Parser.InterfaceGenericMethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#interfaceGenericMethodDecl}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceGenericMethodDecl(Java6Parser.InterfaceGenericMethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#voidInterfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterVoidInterfaceMethodDeclaratorRest(Java6Parser.VoidInterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#voidInterfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitVoidInterfaceMethodDeclaratorRest(Java6Parser.VoidInterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(Java6Parser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(Java6Parser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(Java6Parser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(Java6Parser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(Java6Parser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(Java6Parser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constantDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaratorsRest(Java6Parser.ConstantDeclaratorsRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constantDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaratorsRest(Java6Parser.ConstantDeclaratorsRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constantDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaratorRest(Java6Parser.ConstantDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constantDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaratorRest(Java6Parser.ConstantDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(Java6Parser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(Java6Parser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(Java6Parser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(Java6Parser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(Java6Parser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(Java6Parser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(Java6Parser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(Java6Parser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void enterPackageOrTypeName(Java6Parser.PackageOrTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void exitPackageOrTypeName(Java6Parser.PackageOrTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstantName(Java6Parser.EnumConstantNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstantName(Java6Parser.EnumConstantNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(Java6Parser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(Java6Parser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(Java6Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(Java6Parser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(Java6Parser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(Java6Parser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(Java6Parser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(Java6Parser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(Java6Parser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(Java6Parser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(Java6Parser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(Java6Parser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(Java6Parser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(Java6Parser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(Java6Parser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(Java6Parser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(Java6Parser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(Java6Parser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#formalParameterDecls}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterDecls(Java6Parser.FormalParameterDeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#formalParameterDecls}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterDecls(Java6Parser.FormalParameterDeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#formalParameterDeclsRest}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterDeclsRest(Java6Parser.FormalParameterDeclsRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#formalParameterDeclsRest}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterDeclsRest(Java6Parser.FormalParameterDeclsRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(Java6Parser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(Java6Parser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(Java6Parser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(Java6Parser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitConstructorInvocation(Java6Parser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitConstructorInvocation(Java6Parser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(Java6Parser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(Java6Parser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(Java6Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(Java6Parser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(Java6Parser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(Java6Parser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(Java6Parser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(Java6Parser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(Java6Parser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(Java6Parser.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(Java6Parser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(Java6Parser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationName}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationName(Java6Parser.AnnotationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationName}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationName(Java6Parser.AnnotationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairs(Java6Parser.ElementValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairs(Java6Parser.ElementValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(Java6Parser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(Java6Parser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(Java6Parser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(Java6Parser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(Java6Parser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(Java6Parser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(Java6Parser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(Java6Parser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(Java6Parser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(Java6Parser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementDeclaration(Java6Parser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementDeclaration(Java6Parser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementRest(Java6Parser.AnnotationTypeElementRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementRest(Java6Parser.AnnotationTypeElementRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodOrConstantRest(Java6Parser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodOrConstantRest(Java6Parser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodRest(Java6Parser.AnnotationMethodRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodRest(Java6Parser.AnnotationMethodRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationConstantRest(Java6Parser.AnnotationConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationConstantRest(Java6Parser.AnnotationConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(Java6Parser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(Java6Parser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(Java6Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(Java6Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(Java6Parser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(Java6Parser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(Java6Parser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(Java6Parser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(Java6Parser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(Java6Parser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#variableModifiers}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifiers(Java6Parser.VariableModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#variableModifiers}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifiers(Java6Parser.VariableModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Java6Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Java6Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#catches}.
	 * @param ctx the parse tree
	 */
	void enterCatches(Java6Parser.CatchesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#catches}.
	 * @param ctx the parse tree
	 */
	void exitCatches(Java6Parser.CatchesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(Java6Parser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(Java6Parser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(Java6Parser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(Java6Parser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlock(Java6Parser.SwitchBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlock(Java6Parser.SwitchBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(Java6Parser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(Java6Parser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(Java6Parser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(Java6Parser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(Java6Parser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(Java6Parser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(Java6Parser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(Java6Parser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(Java6Parser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(Java6Parser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(Java6Parser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(Java6Parser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(Java6Parser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(Java6Parser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(Java6Parser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(Java6Parser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(Java6Parser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(Java6Parser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(Java6Parser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(Java6Parser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Java6Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Java6Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(Java6Parser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(Java6Parser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(Java6Parser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(Java6Parser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(Java6Parser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(Java6Parser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(Java6Parser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(Java6Parser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocation(Java6Parser.ExplicitGenericInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocation(Java6Parser.ExplicitGenericInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(Java6Parser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(Java6Parser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(Java6Parser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(Java6Parser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArguments(Java6Parser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArguments(Java6Parser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Java6Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(Java6Parser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Java6Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(Java6Parser.ArgumentsContext ctx);
}