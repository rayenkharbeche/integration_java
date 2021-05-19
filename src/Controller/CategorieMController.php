<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use App\Entity\Categorie;
use App\Repository\CategorieRepository;



class CategorieMController extends AbstractController
{
    /**
     * @Route("/Addc/{type_categorie}/", name="Addcateg")
     */
    public function Addc(Request $request ,$type_categorie) :Response
    {
        $entityManager =$this ->getDoctrine()->getManager()->getConnection();
        $RAW_QUERY="insert into categorie (type_categorie) values ($type_categorie) ";
        $statement=  $entityManager-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new \Symfony\Component\Serializer\Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("added");

    }

    /**
     * @Route("/removeCateg/{id}/", name="removeCategorie")
     */
    public function removecate($id)
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="Delete FROM categorie WHERE id='$id'";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("deleted");

    }

    /**
     * @Route("/updatecat/{id}/{type_categorie}", name="updateCM")
     */
    public function updatecat(Request $request,$type_categorie ,$id) :Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="UPDATE categorie SET type_categorie='$type_categorie'  WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("updated");

    }


    /**
     * @Route("/GetCategorie")
     */
    public function index(Request $request): Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="SELECT * FROM categorie ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $cat=$statement->fetchAll();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        $formatted = $serializer->normalize(['categorie' => $cat]);
        return new JsonResponse($formatted);

    }
}
