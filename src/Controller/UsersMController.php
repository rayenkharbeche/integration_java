<?php

namespace App\Controller;


use App\Entity\Users;
//use phpDocumentor\Reflection\DocBlock\Serializer;
use App\Repository\UsersRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Serializer;


class UsersMController extends AbstractController
{
    /**
     * @Route("/singup", name="app_registerM")
     */
    public function singupAction(Request $request){
        //, UserPasswordEncoderInterface $passwordEncoder)

       $email = $request->query->get("email");
       $roles = $request->query->get("roles");
       $password = $request->query->get("password");
       $cin = $request->query->get("cin");
       $username = $request->query->get("username");

       //controlle 3al mail lezem @
      if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
             return new Response("email invalide");
        }
$user = new Users();
      $user->setEmail($email);
      $user->setRoles(array($roles));
      /*  $user->setPassword(
            $passwordEncoder->encodePassword(
                $user,
                $password
            )
        );*/
      $user->setPassword($password);
      $user->setActivationToken(true);
      $user->setCin($cin);
      $user->setUsername($username);

      try{
          $em = $this->getDoctrine()->getManager();
          $em->persist($user);
          $em->flush();
          return new JsonResponse("account is created", 200);// 200 ya3tini http result du server ok
      }catch(\Exception $ex){
          return new Response("exception".$ex->getMessage());
      }
    }

    /**
     * @Route("/singin", name="app_loginM")
     */

    public function signinAction(Request $request){
                                 //UserPasswordEncoderInterface $passwordEncoder) {
        $email = $request->query->get("email");
        $password = $request->query->get("password");

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Users::class)->findOneBy(['email'=>$email]);//bch nlawj ala user b username ta3o fi base s'il existe njibo

        //ken l9ito f base
        if($user){

            //lazm n9arn password zeda madamo crypté nesta3mlo password_verify
       // if(password_verify($password,$user->getPassword())){

          if($password == $user->getPassword()) {
                $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
                $formatted = $serializer->normalize(['users' => $user]);
                return new JsonResponse($formatted);
            }
            else {
             // $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
             //  $formatted = $serializer->normalize(['users' => $user]);
            //   return new JsonResponse($formatted);
               return new Response("passowrd not found");
            }
        }
        else {
          /*  $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
            $formatted = $serializer->normalize(['users' => $user]);
            return new JsonResponse($formatted);*/
           return new Response("failed");//ya3ni username/pass mch s7a7

        }
    }

    /**
     * @Route("edituserM" , name="app_gestion_profile")
     */
    public function editUser(Request $request )
        //, UserPasswordEncoderInterface $passwordEncoder)
    {
        $id = $request->get("id");
        $username = $request->query->get("username");
        $email = $request->query->get("email");
        $password = $request->query->get("password");
        $roles = $request->query->get("roles");
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Users::class)->find($id);

        //bon l modification bch na3mlouha bel image ya3ni kif tbadl profile ta3ik tzid image
      /* if($request->files->get("photo")!= null) {

            $file = $request->files->get("photo");//njib image fi url
            $fileName = $file->getClientOriginalName();//nom ta3ha

            //taw na5ouha w n7otaha fi dossier upload ely tet7t fih les images en principe te7t public folder
            $file->move(
                $fileName
            );
            $user->setPhoto($fileName);
        }*/

        $user->setUsername($username);
        $user->setPassword($password);
       /*$user->setPassword(
            $passwordEncoder->encodePassword(
                $user,
                $password
            )
        );*/
        $user->setEmail($email);
       $user->setRoles([$roles]);
        $user->setActivationToken(true);

        try{
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return new JsonResponse("success", 200);// 200 ya3tini http result du server ok
        }catch(\Exception $ex){
            return new Response("fail".$ex->getMessage());
        }
    }

    /**
     * @Route("/GetUsers")
     */
    public function index(Request $request): Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="SELECT * FROM users ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $user=$statement->fetchAll();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        $formatted = $serializer->normalize(['users' => $user]);
        return new JsonResponse($formatted);

    }


    /**
     * @Route("/RemoveusertMobile/{id}/", name="deleteCommentMobile")
     */

    public function remove($id)
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="Delete FROM users WHERE id='$id'";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("deleted");

    }



}
